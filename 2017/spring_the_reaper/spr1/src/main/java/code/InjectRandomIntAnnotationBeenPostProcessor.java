package code;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by andrew on 20.05.2017.
 */
public class InjectRandomIntAnnotationBeenPostProcessor implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException
    {
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field fld:fields)
        {
            InjectRandomInt annot = fld.getAnnotation( InjectRandomInt.class );
            if (annot != null)
            {
                int rnd = annot.min() + Math.abs(new Random().nextInt())  % (annot.max() - annot.min());
                fld.setAccessible(true);
                ReflectionUtils.setField( fld, bean, rnd );
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException
    {
        return bean;
    }
}
