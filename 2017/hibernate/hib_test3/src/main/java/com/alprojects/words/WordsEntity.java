package com.alprojects.words;

import org.apache.commons.lang.ObjectUtils;

import javax.persistence.*;

/**
 * Created by andrew on 10.06.2017.
 */
@Entity
@Table(name = "words", schema = "words", catalog = "")
public class WordsEntity
{
    private int id;
    private String text;
    private LangsEntity langsByLangId;
    private Integer version;


    public WordsEntity()
    {}

    public WordsEntity( String text, LangsEntity lang )
    {
        this.text = text;
        this.langsByLangId = lang;
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

    @Basic
    @Column(name = "Text", nullable = false, length = -1)
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordsEntity that = (WordsEntity) o;

        /*
        if (id != that.id) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return true;
        */

        // return text != null ? text.equals(that.text) : that.text == null;
        return
                ObjectUtils.equals(this.langsByLangId, that.langsByLangId)
                &&
                ObjectUtils.equals(this.getText(), that.langsByLangId);
    }

    @Override
    public int hashCode()
    {
        return 31 * ObjectUtils.hashCode(this.getLangsByLangId()) + ObjectUtils.hashCode(this.getText());
    }

    @ManyToOne
    @JoinColumn(name = "LangId", referencedColumnName = "Id", nullable = false)
    public LangsEntity getLangsByLangId()
    {
        return langsByLangId;
    }

    public void setLangsByLangId(LangsEntity langsByLangId)
    {
        this.langsByLangId = langsByLangId;
    }

    @Override
    public String toString()
    {
        return String.format("Word [id:%d,text:%s,Lang:%s]", id, text, langsByLangId);
    }
}
