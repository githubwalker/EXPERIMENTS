package com.alprojects.jms_publisher;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class JMSMessageProducer {
	private JmsTemplate tpl;
	private int msgCount = 0; 
	
	public void SetTemplate( JmsTemplate tpl )
	{
		this.tpl = tpl;
	}

	public void generateMessages()
	{
		for ( int i = 0; i < msgCount; i ++ )
		{
			final String str2besent = "The string #" + Integer.toString(i);
			final int ii = i;
			
			tpl.send( new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage tm = session.createTextMessage();
					tm.setStringProperty("msg", str2besent);
					tm.setIntProperty("number", ii);
					return tm;
				}
			} );
		}
	}
	
	public void setMessageCount( int msgCount )
	{
		this.msgCount = msgCount;
	}
}
