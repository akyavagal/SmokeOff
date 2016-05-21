package com.teamgreen.pollconapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "VEHICLE", uniqueConstraints = @UniqueConstraint(columnNames = 
{
    "ENGINE_NO", "CHASSIS_NO"}
)
)
public class Vehicle implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String make;
    private String model;
    private Date makeDate;
    
    @Column(name = "MAKE_DATE", nullable = false)
    public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	private String engineNumber;
    private String chassisNo;
    private String vehicleNumber;
    private String testStatus;

    public Vehicle()
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

    @Column(name = "MAKE", nullable = false)
    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    @Column(name = "MODEL", nullable = false)
    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

  

  

	@Column(name = "ENGINE_NO", nullable = false)
    public String getEngineNumber()
    {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber)
    {
        this.engineNumber = engineNumber;
    }

    @Column(name = "CHASSIS_NO", nullable = false)
    public String getChassisNo()
    {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo)
    {
        this.chassisNo = chassisNo;
    }

    @Column(name = "VEHICLE_NO", unique = true, nullable = false) //lets make it a combination of engine_no + chassis_no to start with

    public String getVehicleNumber()
    {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }

    @Column(name = "TESTED_STATUS", nullable = false) //OK 

    public String getTestStatus()
    {
        return testStatus;
    }

    public void setTestStatus(String testStatus)
    {
        this.testStatus = testStatus;
    }
}
