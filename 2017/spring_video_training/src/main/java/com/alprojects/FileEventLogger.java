package com.alprojects;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by andrew on 04.06.2017.
 */
public class FileEventLogger implements IEventLogger
{
    protected String fileName;
    protected File file;
    protected String sep = System.getProperty("line.separator");

    public FileEventLogger( String fileName )
    {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event msg)
    {
        try
        {
            FileUtils.writeStringToFile(file, msg.toString(), true );
            FileUtils.writeStringToFile(file, sep, true );
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void init() throws IOException
    {
        file = new File(this.fileName);
        if (!file.canWrite())
            System.out.println( String.format("file %s is unaccessible for writing", this.fileName) );
    }
}
