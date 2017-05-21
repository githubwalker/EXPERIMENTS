package com.alprojects.code.j8features;

/**
 * Created by andrew on 08.05.2017.
 */
public interface Bar
{
    default void Task()
    {
        System.out.println( "Foo" );
    }
}
