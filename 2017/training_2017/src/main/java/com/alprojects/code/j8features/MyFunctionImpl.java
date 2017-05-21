package com.alprojects.code.j8features;

import java.util.function.Function;

/**
 * Created by andrew on 08.05.2017.
 */
public class MyFunctionImpl implements Function<String, Integer>, Comparable<String>
{
    @Override
    public Integer apply(String s)
    {
        return null;
    }

    @Override
    public <V> Function<V, Integer> compose(Function<? super V, ? extends String> before)
    {
        return null;
    }

    @Override
    public <V> Function<String, V> andThen(Function<? super Integer, ? extends V> after)
    {
        return null;
    }

    @Override
    public int compareTo(String o)
    {
        return 0;
    }
}
