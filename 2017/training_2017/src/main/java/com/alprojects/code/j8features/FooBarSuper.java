package com.alprojects.code.j8features;

/**
 * Created by andrew on 08.05.2017.
 */

public class FooBarSuper implements Foo, Bar
{
    @Override
    public void Task()
    {
        Foo.super.Task();
    }
}
