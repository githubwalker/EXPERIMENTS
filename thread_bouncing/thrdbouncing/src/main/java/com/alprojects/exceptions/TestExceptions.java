package com.alprojects.exceptions;


public class TestExceptions {
	
	public static void canThrow() // throws FileNotFoundException
	{
		// throw new FileNotFoundException( "123" );
		// throw new RuntimeException( "123" );
		throw new Error("123");
	}
	
	@SuppressWarnings({ "finally" })
	public static void CouldThrowException() {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            throw e;
        } finally {
            throw new IllegalStateException();
        }
    }	

	public static void testExceptions()
	{
		try
		{
			CouldThrowException();
		}
		catch ( Throwable th  )
		{
/*			
			if ( th instanceof NullPointerException )
			{
				System.out.println( "It was NullPointerException" );
			}
			else if ( th instanceof IllegalStateException )
			{
				System.out.println( "It was IllegalStateException" );
			}
*/			
			System.out.println( th.getClass().toString() ); 
		}
	}
}


