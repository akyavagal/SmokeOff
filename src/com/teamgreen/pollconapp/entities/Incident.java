package com.teamgreen.pollconapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "INCIDENT")
public class Incident
{
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    private int id;
    private String regNumber;
    private String reason;

    public Incident()
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

    @Column(name = "VEHICLE_REG_NO", nullable = false)
    public String getRegNumber()
    {
        return regNumber;
    }

    public void setRegNumber(String regNumber)
    {
        this.regNumber = regNumber;
    }

    @Column(name = "INCIDENT_REASON", nullable = false)
    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
}
