package RecursiveTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by andrew on 22.05.2017.
 */
public class TestRecursiveTask
{
    private List<Integer> _testarray;
    Integer _checksum;

    @Before
    public void Before()
    {
        Random rnd = new Random();
        _testarray = Stream
            .generate( () ->  rnd.nextInt(100) )
            .limit(1000000)
            .collect(Collectors.toList());

        _checksum = _testarray.stream().reduce((x, y) -> x + y).get();
    }

    @Test
    public void ParallelSumIsOk()
    {
        MyRecursiveSumTask task = new MyRecursiveSumTask(_testarray);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        Integer result = forkJoinPool.invoke(task);
        Assert.assertEquals( _checksum, result );
    }

    @Test
    public void ForkJoinFasterThanSimpleRun() throws IOException
    {
        CalcTime.result simpleRun = new CalcTime.result();
        Integer simpleResult;

        try(CalcTime ignored = new CalcTime(simpleRun))
        {
            simpleResult = 0;
            for (Integer a : _testarray)
                simpleResult += a;
        }

        Integer forkJoinResult;
        CalcTime.result forkJoinRun = new CalcTime.result();
        int nProc = Runtime.getRuntime().availableProcessors();

        try(CalcTime ignored = new CalcTime(forkJoinRun))
        {
            MyRecursiveSumTask task = new MyRecursiveSumTask(_testarray);
            ForkJoinPool forkJoinPool = new ForkJoinPool(nProc);
            forkJoinResult = forkJoinPool.invoke(task);
        }

        Assert.assertEquals( simpleResult, forkJoinResult ); // make java not to optimize cycles
        Assert.assertTrue( forkJoinRun._value <  simpleRun._value);
    }
}
