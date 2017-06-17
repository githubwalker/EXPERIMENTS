package TestSerialize;

import java.io.*;

/**
 * Created by andrew on 03.06.2017.
 */
public class TestSerialize
{

    public static void DoTest() throws IOException, ClassNotFoundException
    {
        Obj4Ser h = new Obj4Ser();
        h.setStr("123");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(h);
        byte b[] = bos.toByteArray();
        out.close();
        bos.close();

        h.setStr("456");

        Obj4Ser h2;
        ByteArrayInputStream bis = new ByteArrayInputStream(b);
        ObjectInput in = new ObjectInputStream(bis);
        h2 = (Obj4Ser) in.readObject();

        if ( h.getStr().equals(h2.getStr()) )
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("Failed");
        }
    }
}
