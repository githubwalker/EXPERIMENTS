package com.alprojects.JmsAsyncTest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.qpid.jms.JmsConnectionFactory;

import com.alprojects.JmsAsyncTest.TextListener;

//topic://test_topic

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JMSException, InterruptedException
    {
		final String TOPIC_PREFIX = "topic://";
		
        String user = env("ACTIVEMQ_USER", "admin");
        String password = env("ACTIVEMQ_PASSWORD", "password");
        String host = env("ACTIVEMQ_HOST", "localhost");
        int port = Integer.parseInt(env("ACTIVEMQ_PORT", "5672"));

        String connectionURI = "amqp://" + host + ":" + port;
        String destinationName = arg(args, 0, "topic://event");
		
		ConnectionFactory    connectionFactory = null;
		Connection           connection = null;
		Session              session = null;
		Topic                topic = null;
		MessageConsumer      msgConsumer = null;
		TextListener         topicListener = null;

		connectionFactory = new JmsConnectionFactory(connectionURI);
		connection = connectionFactory.createConnection( user, password );
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = null;
		if (destinationName.startsWith(TOPIC_PREFIX)) {
			destination = session.createTopic(destinationName
					.substring(TOPIC_PREFIX.length()));
		} else {
			destination = session.createQueue(destinationName);
		}

		msgConsumer = session.createConsumer(destination);
        topicListener = new TextListener( connectionFactory, connection, session, topic, msgConsumer );
        msgConsumer.setMessageListener(topicListener);
		connection.start();
		
    	
		Thread.sleep(1000 * 20);
		
		connection.stop();
		connection.close();
    }
    
    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if (rc == null)
            return defaultValue;
        return rc;
    }

    private static String arg(String[] args, int index, String defaultValue) {
        if (index < args.length)
            return args[index];
        else
            return defaultValue;
    }
}


