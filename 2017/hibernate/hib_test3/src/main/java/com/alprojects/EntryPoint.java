package com.alprojects;

import com.alprojects.code.TestHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by andrew on 10.06.2017.
 */

public class EntryPoint
{
    private static final SessionFactory ourSessionFactory;

    static
    {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException
    {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception
    {
        try
        {
            TestHibernate.TestEnumDatabase();
            TestHibernate.TestAddTranslation( "feasible", "english", "осуществимый", "russian" );
            TestHibernate.TestAddTranslation( "invisible", "english", "невидимый", "russian" );
            TestHibernate.TestRaceCondition();
        }
        finally
        {
            ourSessionFactory.close();
        }
    }
}

