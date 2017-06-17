package com.alprojects.singleton;

import java.io.Serializable;

/**
 * Created by andrew on 03.06.2017.
 */
public class MySingleton implements Serializable
{
    /*
    private static class SigletonHelper
    {
        static transient MySingleton instance = new MySingleton();
    }
    */

    private String info;

    public MySingleton()
    {
        System.out.println( "Initializing MySingleton" );
    }

    protected Object readResolve()
    {
        return getInstance();
    }

    static MySingleton inst;

    public static MySingleton getInstance()
    {
        // return SigletonHelper.instance;
        if (inst == null)
            inst = new MySingleton();
        return inst;
    }

    public void DoSomething()
    {
        System.out.println( "DoSomething" );
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
