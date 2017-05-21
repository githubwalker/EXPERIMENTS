package com.alprojects.code.j8features;

import java.util.function.Predicate;

/**
 * Created by andrew on 08.05.2017.
 */
public class TestPredicate
{
    public void TestPred()
    {
        Predicate<String> isLenMoreThan4 = s -> s.length() > 4;

        if ( isLenMoreThan4.test("test") )
        {

        }
    }
}
