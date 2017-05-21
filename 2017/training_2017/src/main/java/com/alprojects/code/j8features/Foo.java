package com.alprojects.code.j8features;

/**
 * Created by andrew on 08.05.2017.
 */
public interface Foo
{
    default void Task()
    {
        System.out.println( "Foo" );
    }
}
