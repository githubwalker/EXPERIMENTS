package com.alprojects.threads;

import org.asynchttpclient.*;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by andrew on 08.05.2017.
 */
public class TestDownload
{
    public void DoDownload(String url )
    {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        asyncHttpClient.prepareGet(url).execute(new AsyncCompletionHandler<Response>()
        {
            @Override
            public Response onCompleted(Response response) throws Exception
            {
                System.out.println( response );
                asyncHttpClient.close();
                return response;
            }

            @Override
            public void onThrowable(Throwable t){
                // Something wrong happened.
                System.err.println( String.format("Something wrong happened : '%s'", t.getMessage()) );
                try
                {
                    asyncHttpClient.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }
}
