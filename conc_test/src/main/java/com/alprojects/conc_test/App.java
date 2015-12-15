package com.alprojects.conc_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Hello world!
 *
 */
public class App 
{
	public static class ThrPusher extends Thread 
	{
		private concurrent_queue<Integer> the_list_;
		private CountDownLatch cdl_;
		private AtomicInteger ai_;
		private int maxcount_;
		
		public ThrPusher( concurrent_queue<Integer> the_list, CountDownLatch cdl, AtomicInteger ai, int maxcount ) 
		{
			this.the_list_ = the_list;
			this.cdl_ = cdl;
			ai_ = ai;
			maxcount_ = maxcount;
		}
		
		private void doTests() throws InterruptedException, BrokenBarrierException
		{
			cdl_.await();
			
			for ( int i = 0; i < maxcount_; i ++ )
			{
				Integer val = ai_.getAndIncrement();
				the_list_.push( val );
			}
		}
		
		@Override
		public void run() 
		{
			try {
				doTests();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static class ThrPopper extends Thread
	{
		private concurrent_queue<Integer> the_list_;
		private CountDownLatch cdl_;
		private int maxcount_;
		private HashSet<Integer> popped_numbers_ = null;
		
		public ThrPopper( concurrent_queue<Integer> the_list, CountDownLatch cdl, int maxcount ) 
		{
			this.the_list_ = the_list;
			this.cdl_ = cdl;
			maxcount_ = maxcount;
			
			int nHashSize = maxcount / 2;
			if ( nHashSize > 1 )
				popped_numbers_ = new HashSet<Integer>( nHashSize );
			else
				popped_numbers_ = new HashSet<Integer>();
		}

		private void doTests() throws InterruptedException, BrokenBarrierException
		{
			cdl_.await();
			
			concurrent_queue.BoolResult br = new concurrent_queue.BoolResult();
			
			for ( int i = 0; i < maxcount_; i ++ )
			{
				Integer popint = the_list_.pop(br);
				if ( br.getResult() )
				{
					popped_numbers_.add(popint);
				}
			}
		}
		
		@Override
		public void run() 
		{
			try {
				doTests();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Set<Integer> getCollectedInts() 
		{
			return popped_numbers_;
		}
	}
	
	private static void testMultithreading4list_helper( parsed_params pp ) throws InterruptedException {
		concurrent_queue<Integer> ls = new concurrent_queue<Integer>();
		CountDownLatch cdl = new CountDownLatch(1);
		ArrayList<ThrPusher> pushers = new ArrayList<ThrPusher>();
		ArrayList<ThrPopper> poppers = new ArrayList<ThrPopper>();
		AtomicInteger ai = new AtomicInteger(0);
		TreeSet<Integer> ts = new TreeSet<Integer>();
		
		final int nPushThreads = pp.nPushThreads;
		final int nPopThreads = pp.nPopThreads;
		final int nIterations = pp.nIterations; 

		System.err.println( "[INFO] Number push threads to run :" + Integer.toString(nPushThreads) );
		System.err.println( "[INFO] Number pop threads to run :" + Integer.toString(nPopThreads) );
		System.err.println( "[INFO] Number interations in each thread to go :" + Integer.toString(nIterations) );
		System.err.println("-------------------------------------");
		System.err.println();
		
		for ( int i = 0; i < nPushThreads; i ++ )
			pushers.add(new ThrPusher( ls, cdl, ai, nIterations ));
		
		for ( int i = 0; i < nPopThreads; i ++ )
			poppers.add(new ThrPopper( ls, cdl, nIterations ));
		
		for( ThrPusher thr : pushers )
			thr.start();
		
		for( ThrPopper thr : poppers )
			thr.start();
		
		cdl.countDown();
		
		for( ThrPusher thr : pushers )
			thr.join();
		
		for( ThrPopper thr : poppers )
			thr.join();

		System.err.println( "[INFO] " + Integer.toString(ls.size()) +  " items left on queue"  );
		System.err.println( "[INFO] " + Integer.toString(nPushThreads * nIterations) +  " total numbers expected to be handled"  );
		System.err.println( "[INFO] push collision counter :" +  Integer.toString(ls.getPushCollisionCounter()) );
		System.err.println( "[INFO] pop collision counter :" +  Integer.toString(ls.getPopCollisionCounter())  );

		double nPushCollisionsPerc = 100.0 *  
				((double)ls.getPushCollisionCounter()) / ((double)nPushThreads * (double)nIterations);
		
		double nPopCollisionsPerc = 100.0 * 
				((double)ls.getPopCollisionCounter()) / ((double)nPopThreads * (double)nIterations);
		
		System.err.println( "[INFO] push collision percentage :" +  Double.toString(nPushCollisionsPerc) );
		System.err.println( "[INFO] pop collision percentage :" +  Double.toString(nPopCollisionsPerc) );
		
		while( !ls.isempty() )
		{
			Integer icur = ls.pop();
			if ( ts.contains(icur) )
				System.err.println( "[FAIL] : " + icur.toString() + "already accounted" );
			else
				ts.add(icur);
		}
		
		for ( ThrPopper thr : poppers )
		{
			Set<Integer> ints = thr.getCollectedInts();
			for ( Integer icur : ints )
			{
				if ( ts.contains(icur) )
					System.err.println( "[FAIL] : " + icur.toString() + "already accounted" );
				else
					ts.add(icur);
			}
		}
		
		if ( ts.size() != nPushThreads * nIterations ) 
		{
			System.err.println( "[FAIL] : got size (" +  Integer.toString(ts.size()) + ", but expected " + Integer.toString(nPushThreads * nIterations) + ")" );
		}
		
		Integer prevint = -1;
		for( Integer icur : ts )
		{
			if ( prevint + 1 != icur )
			{
				System.err.println( "[FAIL] : " + Integer.toString(prevint + 1) + " missing" );
			}
			
			prevint = icur;
		}
		
		System.err.println( "Done checking" );
	}
	
	public static void testMultithreading4list( parsed_params pp  ) {
		try {
			testMultithreading4list_helper( pp );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------
	// option parsing
	// http://stackoverflow.com/questions/9304431/how-to-specify-multiple-options-using-apache-commons-cli
	
	static class parsed_params
	{
		public int nPushThreads;
		public int nPopThreads;
		public int nIterations;
		public boolean bHelp = true;
		
		public parsed_params()
		{
		}
		
		public parsed_params( int nPushThreads, int nPopThreads, int nIterations )
		{
			this.nPushThreads = nPushThreads;
			this.nPopThreads = nPopThreads;
			this.nIterations = nIterations;
			this.bHelp = false;
		}
		
		public boolean isHelp() 
		{
			return this.bHelp;
		} 
	}
	
	private static int intParserHelper( String str, String msg ) throws Exception
	{
		try
		{
			return Integer.parseUnsignedInt(str);
		}
		catch ( NumberFormatException nfe )
		{
			throw new Exception( 
					msg + ": failed to parse " + ( str == null ? "<null>" : str ) + ": " + nfe.getMessage() );
		}
	}

	static Options ops;
	static Option opPush;
	static Option opPop;
	static Option opIters; 
	static Option opHelp;
	
	static
	{
		ops = new Options();
		opPush = Option.builder("u").longOpt("push-threads").desc("number of push threads to run").required().hasArg().build();
		opPop = Option.builder("o").longOpt("pop-threads").desc("number of pop threads to run").required().hasArg().build();
		opIters = Option.builder("i").longOpt("iterations").desc("number of iteration to run in each push/pop thread").required().hasArg().build(); 
		opHelp = Option.builder("h").longOpt("help").desc("produce help").required(false).hasArg(false).build();
		ops.addOption(opPush).addOption(opPop).addOption(opIters).addOption(opHelp);
	}
	
	public static void printHelp()
	{
		HelpFormatter hlp = new HelpFormatter();
		hlp.printHelp("cmdname", ops);
	}
	
	public static parsed_params parseArgs( String[] args ) throws Exception
	{
		CommandLineParser cmdParser = new DefaultParser();
		CommandLine cmdLine = cmdParser.parse(ops, args);
		
		if ( cmdLine.hasOption("h") )
		{
			// printHelp();
			return new parsed_params();
		}
		else 
		{
			String strPushThreads = cmdLine.getOptionValue("u");
			String strPopThreads = cmdLine.getOptionValue("o");
			String strIters = cmdLine.getOptionValue("i");
			
			return new parsed_params( 
					intParserHelper( strPushThreads, "number of push threads" ),
					intParserHelper( strPopThreads, "number of pop threads" ),
					intParserHelper( strIters, "number of iters threads" )
					);
		}
	}
	
    public static void main( String[] args )
    {
    	parsed_params pp = null;
    	try {
			pp = parseArgs( args );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println( "Failed to parse args : " );
			e.printStackTrace();
			printHelp();
			System.exit(1);
		}
    	
    	if ( pp.isHelp() )
    	{
    		printHelp();
    		System.exit(2);
    	}
    	
    	testMultithreading4list( pp );
    }
}

