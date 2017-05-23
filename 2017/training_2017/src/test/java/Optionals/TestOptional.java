package Optionals;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 23.05.2017.
 */

public class TestOptional
{
    @Test
    public void EmptyOptional()
    {
        Optional<String> oString = Optional.empty();
        Assert.assertFalse(oString.isPresent());
    }

    @Test
    public void orElse()
    {
        Assert.assertEquals( Optional.empty().orElse("other"), "other" );
    }

    @Test
    public void Test_orElseGetWhenHasValue()
    {
        Assert.assertEquals( "100", Optional.of("100").orElseGet(() -> "None") );
    }

    @Test
    public void Test_filterWhenStringEmpty()
    {
        Assert.assertFalse(Optional.<String>empty().filter(x->x.contains("notexistantsubstring")).isPresent());
    }

    @Test
    public void Test_filteredOk()
    {
        Assert.assertEquals(
                "Gray fox", Optional.of("Gray fox").filter( x->x.contains("y f") ).orElseGet(()->"not found")
        );
    }

    @Test
    public void Test_filterFailed()
    {
        Assert.assertEquals(
                "not found", Optional.of("Gray fox").filter( x->x.contains("y_f") ).orElseGet(()->"not found")
        );
    }

    @Test
    public void Test_map_filter()
    {
        Assert.assertEquals(
            new Integer(19),
            Optional.of("string length is 19")
                    .map(String::length)
                    .filter(x->x>10)
                    .orElseGet(()->-1)
        );
    }

    @Test
    public void Test_map_filter2()
    {
        Assert.assertEquals(
            new Integer(-1),
            Optional.of("string length is 19")
                    .map(String::length)
                    .filter(x -> x > 19) // this is false so Optional should be empty after this filter
                    .orElseGet(() -> -1)
        );

    }

    @Test
    public void Test_flatMap()
    {
        Assert.assertTrue(Optional.of("test").flatMap(x -> Optional.of(x)).get() instanceof String);
    }
}

