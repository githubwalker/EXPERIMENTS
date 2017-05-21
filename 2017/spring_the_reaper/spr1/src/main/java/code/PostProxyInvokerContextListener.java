package code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by andrew on 21.05.2017.
 */
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    private ConfigurableListableBeanFactory _factory;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        ApplicationContext ctx = event.getApplicationContext();
        String[] names = ctx.getBeanDefinitionNames();

        for(String name: names)
        {
            BeanDefinition bdef = _factory.getBeanDefinition(name);
            String originalClassName = bdef.getBeanClassName();
            try
            {
                Class originalClass = Class.forName(originalClassName);
                Method[] methods = originalClass.getMethods();
                for(Method mtd : methods)
                {
                    Annotation[] annots = mtd.getAnnotations();
                    if (mtd.isAnnotationPresent(PostProxy.class))
                    {
                        Object bean = ctx.getBean(name);

                        if (Proxy.isProxyClass(bean.getClass()))
                        {
                            InvocationHandler ivHandler = Proxy.getInvocationHandler(bean);
                            Object[] args = new Object[]{};
                            Object invoked = ivHandler.invoke(bean, mtd, args);
                        }
                        else
                        {
                            Method[] mtdsOfBean = bean.getClass().getMethods();
                            Method beanMethod = bean.getClass().getMethod(mtd.getName(), mtd.getParameterTypes());
                            beanMethod.invoke(bean);
                        }
                    }
                }
            } catch (Throwable throwable)
            {
                throwable.printStackTrace();
            }
        }
    }
}
