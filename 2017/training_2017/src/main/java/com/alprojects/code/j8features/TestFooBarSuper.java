package com.alprojects.code.j8features;

/**
 * Created by andrew on 08.05.2017.
 */
public class TestFooBarSuper
{
    public void testFooBar()
    {
        FooBarSuper fbs = new FooBarSuper();
        Bar br = fbs;
        Foo fs = fbs;

        br.Task();
        fs.Task();
        // fbs.Task();
    }
}
