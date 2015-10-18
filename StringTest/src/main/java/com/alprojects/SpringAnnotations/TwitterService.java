package com.alprojects.SpringAnnotations;

public class TwitterService implements IMessageService {

	public void SendMessage(String strMessage) {
		// TODO Auto-generated method stub
		System.out.println( "TwitterService sending message..." + strMessage );
	}

}
