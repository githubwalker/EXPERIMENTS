package com.alprojects.TestIterable;

import java.util.*;

/**
 * Created by andrew on 15.05.2017.
 */
public class PlayWithIterable
{
    private transient Integer fld = new Integer(1);

    public PlayWithIterable()
    {
        System.out.println( "inside constructor" );
    }

    public void TestArrayIterators()
    {
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList( 1,2,3,4 )) ;
        // ints.stream().map(x->"{"+x+"}").forEach(System.out::println);
        // Integer rest = ints.stream().reduce( (x,y) -> x+y );
        Integer rest = ints.stream().reduce(0, (x,y) -> x+ y);
        System.out.println(rest);

        Iterator<Integer> it = ints.iterator();
        Integer val = null;
        while( it.hasNext() )
        {
            val = it.next();
            System.out.println( String.format("This values was obtained via iterator: %d", val) );
        }
    }

    public void TestLinkedListIterator()
    {
        List<Integer> ints_list = new LinkedList<Integer>(Arrays.asList(1,2,3,4));
        // ListIterator<Integer> it = ints_list.listIterator(ints_list.size()-1);
        ListIterator<Integer> it = ints_list.listIterator(ints_list.size());
        // ListIterator<Integer> it = ints_list.listIterator();

        // it.add( 6 );
        // it.set( 7 );

        // it.

        while (it.hasPrevious())
        {
            Integer val = it.previous();
            if (val == 2)
            {
                it.add(100);
                // it.remove();
            }
        }

        ListIterator<Integer> it_fwd = ints_list.listIterator();

        while (it_fwd.hasNext())
        {
            Integer val = it_fwd.next();
            if (val == 3)
            {
                it_fwd.add( 200 );
            }
        }

        ints_list.forEach(System.out::println);

        return;
    }

    public void test()
    {
        TestArrayIterators();
        TestLinkedListIterator();
    }
}
