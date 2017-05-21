package com.alprojects.testcloneable;

/**
 * Created by andrew on 18.05.2017.
 */
public class TestUser implements Cloneable
{
    private String _name;
    private transient String _pwd;
    private String _city;

    public TestUser(String _name, String _pwd, String _city)
    {
        this._name = _name;
        this._pwd = _pwd;
        this._city = _city;
    }

    public String get_name()
    {
        return _name;
    }

    public String get_pwd()
    {
        return _pwd;
    }

    public String get_city()
    {
        return _city;
    }

    @Override
    public TestUser clone() throws CloneNotSupportedException
    {
        return (TestUser) super.clone();
    }
}
