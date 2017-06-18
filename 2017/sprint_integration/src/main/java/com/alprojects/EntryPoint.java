package com.alprojects;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by andrew on 18.06.2017.
 */
public class EntryPoint
{

    // https://spring.io/blog/2009/02/13/spring-integration-in-10-minutes
    // https://www.javacodegeeks.com/2015/09/spring-integration-full-example.html
    public static void main(String[] args) throws InterruptedException
    {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-integr-config.xml"))
        {
            /*
            PollableChannel pollableChannel = (PollableChannel) context.getBean("channel");
            pollableChannel.send( new GenericMessage<String>("Spring Integration rocks") );
            Message<?> received = pollableChannel.receive();
            System.out.println( String.format("received: %s", received.toString()) );
            */

            MessageChannel input = (MessageChannel)context.getBean("input");
            PollableChannel output = (PollableChannel)context.getBean("output");
            input.send(new GenericMessage<Object>("Spring Integration rocks"));
            Message<?> reply = output.receive();
            System.out.println( "received: " + reply.getPayload() );
        }
    }
}
