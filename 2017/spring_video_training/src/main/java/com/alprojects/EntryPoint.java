package com.alprojects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by andrew on 04.06.2017.
 */
public class EntryPoint
{
    public static void main(String[] args)
    {
        try(ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml"))
        {
            App app = ctx.getBean(App.class);

            for (int i = 1; i < 15; i++)
            {
                Event evt = ctx.getBean(Event.class);
                EventSeverity sev = EventSeverity.int2Severity(i % 3);

                evt.setId(i);
                evt.setSeverity(sev);
                evt.setMsg(String.format("Some event for user %d", i));

                app.logEvent(evt);
            }
        }

        return;
    }
}
