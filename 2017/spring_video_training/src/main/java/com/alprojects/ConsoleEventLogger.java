package com.alprojects;

/**
 * Created by andrew on 04.06.2017.
 */
public class ConsoleEventLogger implements IEventLogger
{
    @Override
    public void logEvent( Event msg )
    {
        System.out.println( msg );
    }
}
