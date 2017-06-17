package com.alprojects.singleton;

import java.io.*;

/**
 * Created by andrew on 03.06.2017.
 */
public class TestSingleton
{
    public static void DoTest()
    {

        try
        {
            // MySingleton h = MySingleton.getInstance();
            MySingleton h = new MySingleton();
            h.setInfo("123");

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(h);
            byte b[] = bos.toByteArray();
            out.close();
            bos.close();

            h.setInfo("456");

            MySingleton h2;
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            ObjectInput in = new ObjectInputStream(bis);
            h2 = (MySingleton) in.readObject();

            if ( h.getInfo().equals(h2.getInfo()) )
            {
                System.out.println("OK");
            }
            else
            {
                System.out.println("Failed");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
