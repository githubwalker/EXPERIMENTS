package com.alprojects.testcloneable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by andrew on 14.06.2017.
 */
public class TestCollectionCloning
{
    public static void TestClone()
    {
        ArrayList<TestUser> users1 = new ArrayList<TestUser>();
        users1.add(new TestUser( "name1", "pwd1", "city1" ));
        users1.add(new TestUser( "name2", "pwd2", "city2" ));

        ArrayList<TestUser> users2 = (ArrayList<TestUser>)users1.clone();
        return;
    }

    public static void ArrayListLinkedList()
    {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        LinkedList<Integer> a2 = new LinkedList<Integer>();
    }
}
