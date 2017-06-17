package TestStringIntern;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andrew on 23.05.2017.
 */
public class TestStringIntern
{
    private final String _strFinal1 = "intern";
    private final String _strFinal2 = "intern";

    @Test
    public void TestAutoIntern()
    {
        Assert.assertTrue( _strFinal1 == _strFinal2  );
    }

    @Test
    public void TestAutoIntern2()
    {
        Assert.assertTrue( "4/2=" + 4/2 == "4/2=2" );
    }

    @Test
    public void Test_notEqualAfterNew()
    {
        Assert.assertFalse( new Integer(1) == new Integer(1) );
    }

    @Test
    public void Test_IntRefsNotEqualThatGreater127()
    {
        Integer v1 = 128;
        Integer v2 = 128;
        Assert.assertFalse( v1 == v2 ); // note that we are comparing references
    }

    @Test
    public void Test_IntRefsNotEqualThatLessOrEqThan127()
    {
        Integer v1 = 127;
        Integer v2 = 127;
        Assert.assertTrue( v1 == v2 ); // note that we are comparing references
    }
}

