package com.alprojects.producers_consumers;

/**
 * Created by andrew on 13.05.2017.
 */
public class Message
{
    private String _str;

    public Message( String msg )
    {
        _str = msg;
    }

    public String getMessage()
    {
        return _str;
    }
}
