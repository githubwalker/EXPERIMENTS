package com.alprojects.SpringAnnotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(value={"com.alprojects.SpringAnnotations"})
public class DIConfiguration implements Cloneable {

	@Bean()
	public IMessageService getMsgService()
	{
		return new TwitterService();
	}
	
	public DIConfiguration clone() throws CloneNotSupportedException{
		return new DIConfiguration();
	}	
}

