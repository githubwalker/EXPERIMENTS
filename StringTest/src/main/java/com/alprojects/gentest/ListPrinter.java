package com.alprojects.gentest;

import java.util.List;

public class ListPrinter {
	public static <T> void printList( List<T> container )
	{
		int i = 0;
		for( T itm : container )
		{
			System.out.println( "Item " + Integer.toString(i) + "). " + itm.toString() );
			i ++;
		}
	}
}
