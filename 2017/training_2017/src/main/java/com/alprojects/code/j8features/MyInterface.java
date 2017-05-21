package com.alprojects.code.j8features;

/**
 * Created by andrew on 08.05.2017.
 */
public interface MyInterface
{
    String defaultMethod();

    // Integer defaultMethod2(int in);

    static MyInterface defaultTestInterface()
    {
        return () -> "default";
    }
}
