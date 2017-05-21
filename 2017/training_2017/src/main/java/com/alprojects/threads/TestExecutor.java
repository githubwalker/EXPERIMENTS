package com.alprojects.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by andrew on 09.05.2017.
 */
public class TestExecutor
{
    public void Test() throws ExecutionException, InterruptedException
    {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(
                new Callable<Integer>()
                {
                    @Override
                    public Integer call() throws Exception
                    {
                        TimeUnit.SECONDS.sleep(1);
                        return 1000;
                    }
                }
        );


        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);
        es.shutdown();
    }

    public void Test2() throws InterruptedException
    {
        ExecutorService executor = Executors.newWorkStealingPool(15);
        List<Callable<String>> callables = Arrays.asList(
                () -> { Thread.sleep(3000); return "Thr 1";  },
                () -> { Thread.sleep(4000); return "Thr 2";  },
                () -> { Thread.sleep(3000); return "Thr 3";  },
                () -> { Thread.sleep(3000); return "Thr 4";  },
                () -> { Thread.sleep(3000); return "Thr 5";  },
                () -> { Thread.sleep(5000); return "Thr 6";  },
                () -> { Thread.sleep(3000); return "Thr 7";  },
                () -> { Thread.sleep(3000); return "Thr 8";  },
                () -> { Thread.sleep(3000); return "Thr 9";  }
        );

        long millis = System.currentTimeMillis();

        List<String> results =
        executor.invokeAll(callables)
            .stream().map(f ->
        {
            try
            {
                return f.get();
            } catch (InterruptedException | ExecutionException e)
            {
                return "";
            }
        })
        .collect(Collectors.toList())
        ;

        System.out.println( String.format("Time taken to execute 5 threads : %f",
                (double)(System.currentTimeMillis() - millis) / 1000.0 ) );

        System.out.println( "========== Results: ========== " );
        for( String s : results )
            System.out.println( s );

        executor.shutdown();
    }

    private Future<String> getTheFuture()
    {
        CompletableFuture<String> ft = new CompletableFuture<>();
        Executors.newWorkStealingPool(5).submit(
                () ->
                {
                    try
                    {
                        Thread.sleep(5000);
                        ft.complete("Hello");
                    } catch (InterruptedException e)
                    {
                        ft.complete("Failed");
                        e.printStackTrace();
                    }
                }
        );

        return ft;
    }

    static private <T> void pt( Supplier<T> supl )
    {
        System.out.println( String.format("%s: thread is %d", supl.get().toString(), Thread.currentThread().getId()) );
    }

    public void TestCompletableFuture2() throws ExecutionException, InterruptedException
    {
        CompletableFuture<String> ft
                = CompletableFuture.supplyAsync(() -> "Hello!" );

        System.out.println( String.format("Result is : %s", ft.get()) );
    }

    public void TestCompletableFuture3() throws ExecutionException, InterruptedException
    {
        CompletableFuture<Void> ft = CompletableFuture
                .supplyAsync(
                        () ->
                        {
                            pt( () -> "supplyAsync" );
                            return "test";
                        }
                )
                .thenApply(new Function<String, String>()
                {
                    @Override
                    public String apply(String s)
                    {
                        pt( () -> "thenApply" );
                        return s + s;
                    }
                })
                .thenAccept(
                        (x) ->
                        {

                            pt( () -> "thenAccept" );
                            System.out.println(x);
                        }
                )
        ;

        pt( () -> "MainThread" );

        CompletableFuture<Void> ft1 = ft.thenRun( () ->
        {
            pt( () -> "ThenRun" );
        } );

        Void vd = ft.get();

        return;
    }

    private static <T,R> R IfNotNull( T obj, Function<T,R> fn )
    {
        return (obj != null) ? fn.apply(obj) : null;
    }

    private static <T,R> R IfNotNull( T obj, Function<T,R> fn, R defaultValue)
    {
        return (obj != null) ? fn.apply(obj) : defaultValue;
    }

    private static <T,R> R IfNotNull( T obj, Function<T,R> fn, Supplier<R> defaultValue)
    {
        return (obj != null) ? fn.apply(obj) : defaultValue.get();
    }


    public void TestHandleError()
    {
        CompletableFuture<String> cf
                = CompletableFuture.supplyAsync(() -> { throw new RuntimeException("123");})
                    .handle(
                            (o, throwable) ->
                            {
                                System.out.println(String.format("Got the results: %s; error: %s",
                                        IfNotNull(o, Object::toString, ""),
                                        IfNotNull(throwable, Throwable::getMessage, "null"))
                                );
                                return null;
                            }
                    );
        return;
    }

    public void TestCompletableFuture4()
    {
        // CompletableFuture.supplyAsync()
    }

    public void TestCompletableFuture() throws InterruptedException, ExecutionException
    {
        Future<String> ft = getTheFuture();
        System.out.println( String.format("Result is : %s", ft.get()) );
    }
}
