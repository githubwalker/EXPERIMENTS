package com.alprojects.code;

import com.alprojects.EntryPoint;
import com.alprojects.words.LangsEntity;
import com.alprojects.words.TranslationsEntity;
import com.alprojects.words.WordsEntity;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

/**
 * Created by andrew on 10.06.2017.
 */
public class TestHibernate
{
    static private LangsEntity AddOrGetLang(EntityManager em, String langText)
    {
        LangsEntity lang = em.createQuery("select lng from LangsEntity lng where lng.name = :langname", LangsEntity.class)
                .setParameter("langname", langText)
                .getResultList().stream().findFirst().orElse(null);

        if (lang == null)
        {
            lang = new LangsEntity( langText );
            em.persist( lang );
        }

        return lang;
    }

    static private WordsEntity AddOrGetWord(EntityManager em, String text, LangsEntity le)
    {
        WordsEntity word = em.createQuery("select we from WordsEntity we where we.text = :text and we.langsByLangId.name = :langname", WordsEntity.class)
                .setParameter("langname", le.getName())
                .setParameter("text", text)
                .getResultList().stream().findFirst().orElse(null);

        if (word == null)
        {
            word = new WordsEntity(text, le);
            em.persist( word );
        }

        return word;
    }

    static public void TestAddTranslation(
            String w1text, String lang1text,
            String w2text, String lang2text
    )
    {
        try (Session session = EntryPoint.getSession())
        {
            // Transaction tr = session.beginTransaction();
            EntityManager em = null;

            try
            {
                em = session.getEntityManagerFactory().createEntityManager();
                em.getTransaction().begin();

                LangsEntity lang1 = AddOrGetLang(em, lang1text);
                LangsEntity lang2 = AddOrGetLang(em, lang2text);

                WordsEntity w1 = AddOrGetWord(em, w1text, lang1);
                WordsEntity w2 = AddOrGetWord(em, w2text, lang2);

                TranslationsEntity translation = em.createQuery("select te from TranslationsEntity te where te.word1Entity.text = :w1text and te.word2Entity.text = :w2text", TranslationsEntity.class)
                        .setParameter("w1text", w1text)
                        .setParameter("w2text", w2text)
                        .getResultList().stream().findFirst().orElse(null);

                if (translation == null)
                {
                    translation = new TranslationsEntity( w1, w2 );
                    em.persist( translation );
                }

                em.getTransaction().commit();
                System.out.println("Seems done");
            }
            finally
            {
                if (em != null)
                    em.close();
            }

            return;
        }
    }

    // http://massfords.com/Proxy-for-AutoCloseable/
    static public void TestRaceCondition()
    {
        try (
                Session s1 = EntryPoint.getSession();
                Session s2 = EntryPoint.getSession())
        {
            EntityManager em1 = null;
            EntityManager em2 = null;
            try
            {
                em1 = s1.getEntityManagerFactory().createEntityManager();
                em1.getTransaction().begin();

                em2 = s2.getEntityManagerFactory().createEntityManager();
                em2.getTransaction().begin();

                WordsEntity w1 =
                    em1.createQuery("select w from WordsEntity w where w.text like :wtext", WordsEntity.class)
                    .setParameter("wtext", "%invisible%")
                    .getResultList().stream().findFirst().orElse(null);

                WordsEntity w2 =
                    em2.createQuery("select w from WordsEntity w where w.text like :wtext", WordsEntity.class)
                            .setParameter("wtext", "%invisible%")
                            .getResultList().stream().findFirst().orElse(null);

                if (w1 != null)
                    w1.setText( w1.getText() + "1" );

                if (w2 != null)
                    w2.setText( w2.getText() + "1" );

                em1.getTransaction().commit();
                em2.getTransaction().commit();
            }
            finally
            {
                if (em1 != null)
                    em1.close();

                if (em2 != null)
                    em2.close();
            }
        }
    }

    static public void TestEnumDatabase()
    {
        // final Session session = EntryPoint.getSession();
        try (Session session = EntryPoint.getSession())
        {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities())
            {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list())
                {
                    System.out.println("  " + o);
                }
            }
        }
    }
}
