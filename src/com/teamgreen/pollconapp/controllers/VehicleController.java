package com.teamgreen.pollconapp.controllers;

import com.teamgreen.pollconapp.entities.Vehicle;
import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "vehicleController")
@RequestScoped
public class VehicleController implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{GreenAppService}")
    GreenAppService greenAppService;
    private List<Vehicle> vehicleList = null;
    private Vehicle vehicle;

    public VehicleController()
    {
        vehicle = new Vehicle();
    }

    public List<Vehicle> getVehicleList()
    {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public GreenAppService getGreenAppService()
    {
        return greenAppService;
    }

    public void setGreenAppService(GreenAppService greenAppService)
    {
        this.greenAppService = greenAppService;
    }

    @PostConstruct
    public void init()
    {
        vehicleList = getGreenAppService().getAllVehicles();
    }

    public String addVehicle()
    {
    	if(getVehicle()!=null)
    	{
    		String engNo = getVehicle().getEngineNumber();
    		String chassisNo = getVehicle().getChassisNo();
    		getVehicle().setVehicleNumber(engNo+chassisNo);
    	}
    	
        try
        {
            getGreenAppService().createVehicle(getVehicle());

            List<Vehicle> vehicleList = getGreenAppService().getAllVehicles();
            JSFUtils.addInfoMsg("New Vehicle details saved successfully..");
            setVehicleList(vehicleList);
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. Cannot save Vehicle...");
        }

        return null;
    }
}
