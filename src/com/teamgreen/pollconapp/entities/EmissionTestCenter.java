package com.teamgreen.pollconapp.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EmissionTestCenter")
public class EmissionTestCenter implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String contactNumber;
    private String city;
    private String email;
    private String licenseNumber;
    private Date expiryDate;
    private String status; //Active, Suspended

    public EmissionTestCenter()
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

    @Column(name = "CONTACT_NUMBER", unique = true, nullable = false)
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

    @Column(name = "EMAIL", unique = true, nullable = false)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name = "LICENSE_NUMBER", unique = true, nullable = false)
    public String getLicenseNumber()
    {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
    }

    @Column(name = "EXPIRY_DATE", nullable = false)
    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    @Column(name = "TESTER_STATUS", nullable = false)
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
