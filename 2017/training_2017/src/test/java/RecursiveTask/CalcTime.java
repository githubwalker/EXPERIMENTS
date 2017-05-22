package RecursiveTask;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by andrew on 23.05.2017.
 */
class CalcTime implements Closeable
{
    static class result
    {
        long _value;
    }

    long _startTime;
    result _diff;

    CalcTime(result diff)
    {
        _startTime = System.currentTimeMillis();
        _diff = diff;
    }

    @Override
    public void close() throws IOException
    {
        _diff._value = System.currentTimeMillis() - _startTime;
    }
}
