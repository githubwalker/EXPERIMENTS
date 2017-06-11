package com.alprojects.words;

import javax.persistence.*;
import org.apache.commons.lang.ObjectUtils;

/**
 * Created by andrew on 10.06.2017.
 */
@Entity
@Table(name = "translations", schema = "words", catalog = "")
public class TranslationsEntity
{
    private int id;
    private WordsEntity Word1Entity;
    private WordsEntity Word2Entity;
    private Integer version;


    public TranslationsEntity()
    {}

    public TranslationsEntity(WordsEntity w1, WordsEntity w2)
    {
        this.Word1Entity = w1;
        this.Word2Entity = w2;
    }

    @Version
    @Column(name="version", nullable = true)
    public Integer getVersion()
    {
        return version;
    }
    public void setVersion(Integer version)
    {
        this.version = version;
    }

    @ManyToOne
    @JoinColumn(name = "Word1Id", referencedColumnName = "Id", nullable = false)
    public WordsEntity getWord1Entity()
    {
        return Word1Entity;
    }

    public void setWord1Entity(WordsEntity word1Entity)
    {
        Word1Entity = word1Entity;
    }

    @ManyToOne
    @JoinColumn(name = "Word2Id", referencedColumnName = "Id", nullable = false)
    public WordsEntity getWord2Entity()
    {
        return Word2Entity;
    }

    public void setWord2Entity(WordsEntity word2Entity)
    {
        Word2Entity = word2Entity;
    }

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslationsEntity that = (TranslationsEntity) o;

        return
            ObjectUtils.equals( this.getWord1Entity(), that.getWord1Entity() )
            &&
            ObjectUtils.equals( this.getWord2Entity(), that.getWord2Entity() )
        ;

    }

    @Override
    public int hashCode()
    {
        return 31 * ObjectUtils.hashCode(Word1Entity) + ObjectUtils.hashCode(Word2Entity);
    }

    @Override
    public String toString()
    {
        return String.format( "Translation [id=%d, %s->%s]", id, Word1Entity, Word2Entity );
    }
}
