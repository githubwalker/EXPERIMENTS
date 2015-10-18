package com.alprojects.SpringAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyActivity {
	private IMessageService _msgService;

	@Autowired
	public void setService( IMessageService service )
	{
		_msgService = service;
	}
	
	public void preformAction( String strAction )
	{
		_msgService.SendMessage(strAction);
	}
}

