package code;

import javax.annotation.PostConstruct;

/**
 * Created by andrew on 20.05.2017.
 */
@ProfilingProxy
public class SayQuoteImpl implements ISayQuote
{
    @InjectRandomInt(min = 2, max = 7)
    private int _repeat;
    private String _message;

    public SayQuoteImpl()
    {
        System.out.println("[Phase1]");
    }

    public void setMessage(String message)
    {
        _message = message;
    }

    @PostConstruct
    public void init()
    {
        System.out.println(String.format("[Phase2] init() called. repeat = %d", _repeat));
    }

    @PostProxy
    public void Phase3ofConstruction()
    {
        System.out.println("[Phase3]");
    }

    @Override
    public void quote()
    {
        for (int i=0; i < _repeat; i ++)
            System.out.println(_message);
    }
}
