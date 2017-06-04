package com.alprojects;

/**
 * Created by andrew on 04.06.2017.
 */
public class Client
{
    private String clientId;
    private String clientName;
    private String greeting;

    public Client()
    {}

    public String getGreeting()
    {
        return greeting;
    }

    public void setGreeting(String greeting)
    {
        this.greeting = greeting;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
}
