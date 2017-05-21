package TestStreams;

import org.junit.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by andrew on 21.05.2017.
 */
public class LoadTextFile
{
    private static File _tempFile;

    @BeforeClass
    public static void BeforeClass() throws IOException
    {
        _tempFile = File.createTempFile( "temp-text-file", ".txt" );

        String[] lines =
                {
                ";UseSystemProfiles lets you control the location of Far configuration files.",
                ";When 1, configuration will be stored in",
                ";%APPDATA%\\Far Manager - for general configuration"
                };

        FileWriter fw = new FileWriter(_tempFile);

        try (BufferedWriter bw = new BufferedWriter(fw))
        {
            for(String ln : lines)
                bw.write( ln + System.lineSeparator() );
        }
    }

    @AfterClass
    public static void AfterClass()
    {
        boolean bOk = _tempFile.delete();
    }

    @Test
    public void stream8readingNumberOfLinesIsOk() throws IOException
    {
        List<String> rv = Files.lines(Paths.get(_tempFile.getAbsolutePath()) )
                // .map(l-> l.split("\\s+", 2))
                .collect(Collectors.toList());
        Assert.assertTrue( rv.size() == 3 );
    }

    @Test
    public void stream8readingNumberOfWordsIsOk() throws IOException
    {
        Pattern ptn = Pattern.compile("\\s+");
        List<String> rv =
            Files.lines(Paths.get(_tempFile.getAbsolutePath()) )
                    .flatMap(ptn::splitAsStream)
                    .collect(Collectors.toList())
        ;

        Assert.assertTrue( rv.size() == 23 ); // 23 words total in file
    }
}
