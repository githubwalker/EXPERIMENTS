package code;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by andrew on 20.05.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt
{
    int min();
    int max();
}

