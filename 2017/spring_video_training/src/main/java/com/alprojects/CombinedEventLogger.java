package com.alprojects;

import java.util.Collection;

/**
 * Created by andrew on 04.06.2017.
 */
public class CombinedEventLogger implements IEventLogger
{
    private Collection<IEventLogger> loggers;

    public CombinedEventLogger(Collection<IEventLogger> loggers)
    {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event msg)
    {
        for( IEventLogger logger : loggers )
            logger.logEvent(msg);
    }
}

