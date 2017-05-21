package com.alprojects.spr1;

import code.ISayQuote;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by andrew on 20.05.2017.
 */

public class EntryPoint
{

    public static void main(String[] args) throws InterruptedException
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myconfig.xml");
        ISayQuote sayQuote = context.getBean(ISayQuote.class);

        for(;;)
        {
            Thread.sleep(5000);
            System.out.println("--------------------------");
            sayQuote.quote();
        }
    }
}
