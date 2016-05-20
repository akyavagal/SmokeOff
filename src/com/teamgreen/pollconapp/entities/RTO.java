package com.teamgreen.pollconapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "RTO")
public class RTO implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String contactNumber;
    private String city;
    private String email;
    private String rtoCode;

    public RTO()
    {
        super();
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "ADDRESS", nullable = false)
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Column(name = "CONTACT_NUMBER", nullable = false)
    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    @Column(name = "CITY", nullable = false)
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name = "RTO_CODE", nullable = false)
    public String getRtoCode()
    {
        return rtoCode;
    }

    public void setRtoCode(String rtoCode)
    {
        this.rtoCode = rtoCode;
    }
}
