package com.alprojects.JmsAsyncTest;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.qpid.jms.JmsConnectionFactory;



public class TextListener implements MessageListener {
	ConnectionFactory connectionFactory = null;
	Connection connection = null;
	Session session = null;
	Topic topic = null;
	MessageConsumer msgConsumer = null;
	long start;
	long count;

	public TextListener(ConnectionFactory connectionFactory,
			Connection connection, Session session, Topic topic,
			MessageConsumer msgConsumer) {
		this.connectionFactory = connectionFactory;
		this.connection = connection;
		this.session = session;
		this.topic = topic;
		this.msgConsumer = msgConsumer;
		this.start = System.currentTimeMillis();
		this.count = 0;
	}

	public void onMessage(Message msg) {
		try {
			handleMsg(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void handleMsg(Message msg) throws JMSException {
		if (msg instanceof TextMessage) {
			String body = ((TextMessage) msg).getText();
			if ("SHUTDOWN".equals(body)) {
				long diff = System.currentTimeMillis() - start;
				System.out.println(String.format("Received %d in %.2f seconds",
						count, (1.0 * diff / 1000.0)));
				connection.close();
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
				System.exit(1);
			} else {
				try {
					if (count != msg.getIntProperty("id")) {
						System.out.println("mismatch: " + count + "!="
								+ msg.getIntProperty("id"));
					}
				} catch (NumberFormatException ignore) {
				}

				if (count == 1) {
					start = System.currentTimeMillis();
				} else if (count % 1000 == 0) {
					System.out.println(String.format("Received %d messages.",
							count));
				}
				count++;
			}

		} else {
			System.out.println("Unexpected message type: " + msg.getClass());
		}
	}

	static TextListener prepare(
			String connectionURI,
			String destinationName,
			String user,
			String password
	) throws JMSException {
		final String TOPIC_PREFIX = "topic://";
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Topic topic = null;
		MessageConsumer msgConsumer = null;
		TextListener topicListener = null;

		connectionFactory = new JmsConnectionFactory(connectionURI);
		connection = connectionFactory.createConnection(user, password);
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination destination = null;
		if (destinationName.startsWith(TOPIC_PREFIX)) {
			destination = session.createTopic(destinationName
					.substring(TOPIC_PREFIX.length()));
		} else {
			destination = session.createQueue(destinationName);
		}

		msgConsumer = session.createConsumer(destination);
		topicListener = new TextListener(connectionFactory, connection,
				session, topic, msgConsumer);
		msgConsumer.setMessageListener(topicListener);
		connection.start();

		return topicListener;
	}

}
