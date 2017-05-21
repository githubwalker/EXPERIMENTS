package com.alprojects.TestIterable;

/**
 * Created by andrew on 16.05.2017.
 */
public class MySingle
{
    private static MySingle ourInstance = new MySingle();

    public static MySingle getInstance()
    {
        return ourInstance;
    }

    private MySingle()
    {
    }
}
