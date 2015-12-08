package com.alprojects.test_quartz;

// import org.apache.log4j.Logger;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements InterruptableJob {
	
	// private Logger log = new Logger.getLogger();
	// private Logger logger = LoggerFactory.getLogger(MyApp3.class);
	private Logger logger = LoggerFactory.getLogger("STDOUT");

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.debug("MyJob.execute()");
	}

	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		logger.debug("MyJob is being interrupted ...");
	}

}
