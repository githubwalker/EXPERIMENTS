package com.alprojects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

public class App implements ApplicationContextAware
{
    private ApplicationContext appContext;

    private Client client;
    private Map<EventSeverity,IEventLogger> loggers;
    private IEventLogger defaultEventLogger;

    public App(Client client, Map<EventSeverity, IEventLogger> loggers, IEventLogger defaultEventLogger)
    {
        this.client = client;
        this.loggers = loggers;
        this.defaultEventLogger = defaultEventLogger;
    }

    public void logEvent( Event evt )
    {
        Event cloned = appContext.getBean(Event.class);
        BeanUtils.copyProperties(evt, cloned);
        String replaceText =
                (client.getGreeting() != null ? client.getGreeting() : "") +
                cloned.getMsg().replaceAll( client.getClientId(), client.getClientName());
        cloned.setMsg(replaceText);

        if (cloned.getSeverity() == null)
        {
            defaultEventLogger.logEvent(cloned);
        }
        else
        {
            IEventLogger logger = loggers.get(cloned.getSeverity());
            if (logger != null)
                logger.logEvent(cloned);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.appContext = applicationContext;
    }
}
