package com.alprojects;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by andrew on 04.06.2017.
 */
public class CachedFileEventLogger extends FileEventLogger
{
    private Queue<Event> que = new LinkedList<>();
    private final int buffSize = 10;

    public CachedFileEventLogger(String fileName)
    {
        super(fileName);
    }

    protected void destroySpringBean()
    {
        flushQue();
    }

    private String depleteQue()
    {
        StringBuilder sb = new StringBuilder();
        while(!que.isEmpty())
        {
            sb.append(que.poll());
            sb.append(sep);
        }

        return sb.toString();
    }

    private void flushQue()
    {
        if (!que.isEmpty())
        {
            try
            {
                FileUtils.writeStringToFile(this.file, depleteQue(), true);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void logEvent(Event msg)
    {
        que.add(msg);
        if (que.size() >= buffSize)
            flushQue();
    }
}
