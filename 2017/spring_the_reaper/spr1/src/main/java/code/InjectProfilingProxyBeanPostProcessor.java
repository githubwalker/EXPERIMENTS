package code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew on 21.05.2017.
 */
public class InjectProfilingProxyBeanPostProcessor implements BeanPostProcessor
{
    ProfilingController _pc = new ProfilingController();
    private Map<String,Class> _classes = new HashMap<>();

    public InjectProfilingProxyBeanPostProcessor() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException
    {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        beanServer.registerMBean(_pc, new ObjectName("profiling", "name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException
    {
        if ( bean.getClass().getAnnotation(ProfilingProxy.class) != null )
            _classes.put(s, bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException
    {
        Class cls = _classes.get(s);
        if (cls != null)
        {
            return Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler()
                    {
                        @Override
                        public Object invoke(
                                Object proxy,
                                Method method,
                                Object[] args) throws Throwable
                        {
                            Object rv;
                            if (_pc.isEnabled())
                            {
                                System.out.println( String.format("[Profiler] Starting %s ...", method.getName()) );
                                rv = method.invoke(bean, args);
                                System.out.println( String.format("[Profiler] Returning from %s", method.getName()) );

                            }
                            else
                            {
                                rv = method.invoke(bean, args);
                            }
                            return rv;
                        }
                    }
            );
        }
        return bean;
    }
}
