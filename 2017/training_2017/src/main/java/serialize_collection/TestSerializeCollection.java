package serialize_collection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by andrew on 18.06.2017.
 */
public class TestSerializeCollection
{
    public static void TestItself() throws IOException
    {
        ArrayList<ToBeSerialized> col = new ArrayList<>();
        col.add( new ToBeSerialized("obj1", "obj_1", 1) );
        col.add( new ToBeSerialized("obj2", "obj_2", 2) );
        col.add( new ToBeSerialized("obj3", "obj_3", 3) );
        col.add( new ToBeSerialized("obj4", "obj_4", 4) );
        col.add( new ToBeSerialized("obj5", "obj_5", 5) );

        try(
                FileOutputStream fos = new FileOutputStream("C:\\tmp\\1.out");
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(col);
        }
    }

    public static void TestClone()
    {
        ArrayList<ToBeSerialized> col = new ArrayList<>();
        col.add( new ToBeSerialized("obj1", "obj_1", 1) );
        col.add( new ToBeSerialized("obj2", "obj_2", 2) );
        col.add( new ToBeSerialized("obj3", "obj_3", 3) );
        col.add( new ToBeSerialized("obj4", "obj_4", 4) );
        col.add( new ToBeSerialized("obj5", "obj_5", 5) );

        ArrayList<ToBeSerialized> col1 = (ArrayList<ToBeSerialized>)col.clone();
        ArrayList<ToBeSerialized> col2 = new ArrayList<ToBeSerialized>(col);
    }
}
