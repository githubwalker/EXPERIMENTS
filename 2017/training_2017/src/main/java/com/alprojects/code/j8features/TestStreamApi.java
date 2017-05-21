package com.alprojects.code.j8features;


import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by andrew on 07.05.2017.
 */
public class TestStreamApi
{
    private void SortInParallel( ArrayList<Long> ints )
    {
        Optional<Long> rv
                = ints.parallelStream().reduce((s1,s2)->s1+s2);
                // = ints.stream().reduce((s1,s2)->s1+s2);
        // System.out.println( String.format("Calculated sum is: %d", rv.get()) ) ;
    }

    public void PerformSortTest()
    {
        final int arrsize = 16 * 1024 * 1024 ;
        ArrayList<Long> ints = new ArrayList<>();

        /*
        for(int i = 0; i < arrsize; i++ )
            ints.add( ThreadLocalRandom.current().nextInt(0, arrsize) );
        */
        for(long i = 0; i < arrsize; i++ )
            ints.add( i );

        long millis = System.currentTimeMillis();
        for(int j = 0; j < 100; j ++)
            SortInParallel( ints );
        long millisTaken = System.currentTimeMillis() - millis;
        System.out.println(  String.format("Time taken : %d", millisTaken) );
    }

    public void Test2()
    {
        Function<Integer,Integer> r1 = (Integer x) -> x * 2;
        System.out.println( String.format( "F(%d) = %d ", 100, r1.apply(100) ) );
    }

    public void SortTest()
    {
        final int arrsize = 1000;
        ArrayList<Long> ints = new ArrayList<>();
        for(int i = 0; i < arrsize; i++ )
            ints.add( ThreadLocalRandom.current().nextLong(0, arrsize) );

        ints.sort((o1, o2) -> o1 < o2 ? -1 : 1);
    }

    public void TestComparator()
    {
        ArrayList<String> strings = new ArrayList<String>()
        {{
            add( "Hren" );
            add( "hren" );
            add( "Tests" );
            add( "Tests1" );
            add( "Test2" );
            add( "test333" );
            add( "teSt4" );
            add( "hRen" );
        }};

        // Comparator<String> cmp = String::compareToIgnoreCase;
        strings.sort(String::compareToIgnoreCase);
        System.out.println( "Sorted strings:" );
        strings.stream().forEach(System.out::println);
    }

    // public void Test
    public void ScanFile( String fileName ) throws IOException
    {
        final AtomicInteger cnt = new AtomicInteger(0);
        Files.lines(Paths.get(fileName))
                .map(String::trim)
                .map(line -> Integer.toString(cnt.incrementAndGet())  + ":   " + line )
                .forEach(System.out::println);
    }

    public void PerformTests()
    {
        Map<Integer,String> mp =
        Stream.of(
                new AbstractMap.SimpleEntry<>(1,"1"),
                new AbstractMap.SimpleEntry<>(2,"2"),
                new AbstractMap.SimpleEntry<>(3,"3"),
                new AbstractMap.SimpleEntry<>(4,"4"),
                new AbstractMap.SimpleEntry<>(5,"5")
        )
        .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue) );

        mp.forEach(
                (i,s) -> System.out.println( String.format("key %d : Value %s", i, s) )
        );

        return;
    }

    public void TestStreamOf()
    {
        List<Integer> ints =
                    Stream.of( 1, 2, 3, 4, 5 )
                    .collect(Collectors.toList())
                ;
    }

    public void TestStreamFileList()
    {
        IntSummaryStatistics ss =
            new Random().ints().limit(10).sorted()
                    .map(Integer::new)
                    .summaryStatistics()
                    ;
    }
}
