package com.alprojects;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by andrew on 04.06.2017.
 */
public class Event
{
    private int Id;
    private String msg;
    private Date date;
    private DateFormat df;
    private EventSeverity severity;

    public Event(Date date, DateFormat df)
    {
        this.date = date;
        this.df = df;
    }

    public EventSeverity getSeverity()
    {
        return severity;
    }

    public void setSeverity(EventSeverity severity)
    {
        this.severity = severity;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return String.format( "[id=%d, msg=%s, date=%s]",Id, msg, this.df.format(this.date) );
    }
}

