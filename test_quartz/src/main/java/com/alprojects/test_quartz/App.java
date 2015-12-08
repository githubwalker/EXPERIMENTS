package com.alprojects.test_quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 * Hello world!
 *
 */

// http://examples.javacodegeeks.com/enterprise-java/quartz/spring-quartz-scheduler-example/
// http://www.javacodegeeks.com/2012/07/quartz-2-scheduler-example.html
// http://websystique.com/spring/spring-4-quartz-scheduler-integration-example/

public class App 
{
	public static void persistence()
	{
		// JDBCJobStore jjs = null;
	}
	
    public static void main( String[] args ) throws SchedulerException, InterruptedException
    {
    	JobDetail job =  JobBuilder.newJob(MyJob.class)
    						.withIdentity("MyJob")
    						.build();
    	
    	Trigger trigger = TriggerBuilder.newTrigger().withIdentity("MyJob")
    			.withSchedule(
    					SimpleScheduleBuilder.simpleSchedule()
    					.withIntervalInSeconds(5)
    					.repeatForever()
    					).build();
    	
    	SchedulerFactory sf = new StdSchedulerFactory();
    	Scheduler scheduler = sf.getScheduler();

        System.out.println( "Starting Scheduler ..." );
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
    	
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				
				System.out.println("Job: " + jobKey.getGroup() + ", "
						+ jobKey.getName() + " ("
						+ jobKey.getClass().toString() + ")");
			}
		}

        System.out.println( "Waiting for a 30 sec then stop all jobs ..." );
		Thread.sleep(30000);
		
        System.out.println( "Stopping all jobs ..." );
		scheduler.shutdown( true );

        System.out.println( "Main thread exiting ..." );
    }
}
