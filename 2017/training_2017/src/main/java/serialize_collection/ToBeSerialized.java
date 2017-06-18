package serialize_collection;

import java.io.Serializable;

/**
 * Created by andrew on 18.06.2017.
 */
public class ToBeSerialized implements Serializable, Cloneable
{
    private String _v1;
    private String _v2;
    private int _v3;

    public ToBeSerialized()
    {}

    public ToBeSerialized( String v1, String v2, int v3 )
    {
        _v1 = v1;
        _v2 = v2;
        _v3 = v3;
    }
}
