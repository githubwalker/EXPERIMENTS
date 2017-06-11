package com.alprojects.words;

import org.apache.commons.lang.ObjectUtils;

import javax.persistence.*;

/**
 * Created by andrew on 10.06.2017.
 */
@Entity
@Table(name = "langs", schema = "words", catalog = "")
public class LangsEntity
{
    private int id;
    private String name;
    private Integer version;

    public LangsEntity()
    {}

    public LangsEntity(String text)
    {
        this.name = name;
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
    @Column(name = "Name", nullable = true, length = -1)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LangsEntity that = (LangsEntity) o;

        return ObjectUtils.equals(this.name, that.name);
    }

    @Override
    public int hashCode()
    {
        return ObjectUtils.hashCode(this.name);
    }

    @Override
    public String toString()
    {
        return getName();
    }

}
