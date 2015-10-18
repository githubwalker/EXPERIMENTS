package com.alprojects.SpringAnnotations;

public class EmailService implements IMessageService, Cloneable {

	public void SendMessage(String strMessage) {
		// TODO Auto-generated method stub
		System.out.println( "EmailService sending message... : " + strMessage );
	}

}

