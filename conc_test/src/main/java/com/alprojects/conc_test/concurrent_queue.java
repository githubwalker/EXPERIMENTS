package com.alprojects.conc_test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class concurrent_queue<T> 
{
	public static class BoolResult 
	{
		private boolean bResult = false;
		
		public boolean getResult() 
		{
			return bResult;
		}
		
		public void setResult( boolean bResult )
		{
			this.bResult = bResult;
		}
	}
	
	private static class Node<T> 
	{
		public volatile T value = null;
		public volatile Node<T> next = null; // next --> 
	}

	private AtomicReference< Node<T> > head_ = new AtomicReference< Node<T> >( new Node<T>() );
	private AtomicReference< Node<T> > tail_ = new AtomicReference< Node<T> >( head_.get() );
	private AtomicInteger size_ = new AtomicInteger( 0 );
	private AtomicInteger push_collision_counter_ = new AtomicInteger( 0 );
	private AtomicInteger pop_collision_counter_ = new AtomicInteger( 0 );
	
	
	public int push( T obj )
	{
		Node<T> newnode = new Node<T>();

		for(;;)
		{
			Node<T> prevtail = tail_.get();
			
			if ( tail_.compareAndSet(prevtail, newnode) )
			{
				prevtail.value = obj;
				prevtail.next = newnode;
				break;
			}
			else
			{
				push_collision_counter_.incrementAndGet();
			}
		}
		
		return size_.incrementAndGet();
	}

	public boolean isempty()
	{
		return head_.get() == tail_.get();
	}
	
	public T pop( BoolResult result )
	{
		while(head_.get() != tail_.get())
		{
			Node<T> savehead = head_.get();
			Node<T> savenext = savehead.next;
			if ( savenext != null )
			{
				if ( head_.compareAndSet(savehead, savenext) )
				{
					T rv = savehead.value;
					savehead.value = null;
					savehead.next = null;
					size_.decrementAndGet();
					if (result != null)
						result.setResult(true);
					return rv;
				}
				else
				{
					pop_collision_counter_.incrementAndGet();
				}
			}
			else
			{
				pop_collision_counter_.incrementAndGet();
			}
		}

		if (result != null)
			result.setResult(false);
		return null;
	}
	
	public T pop()
	{
		return pop(null);
	}
	
	public int size() 
	{
		return size_.get();
	}
	
	public int getPushCollisionCounter() 
	{
		return this.push_collision_counter_.get();
	}
	
	public int getPopCollisionCounter() 
	{
		return this.pop_collision_counter_.get();
	}
}
