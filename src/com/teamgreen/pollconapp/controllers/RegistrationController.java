package com.teamgreen.pollconapp.controllers;

import com.teamgreen.pollconapp.entities.Incident;
import com.teamgreen.pollconapp.entities.Owner;
import com.teamgreen.pollconapp.entities.Registration;
import com.teamgreen.pollconapp.entities.Test;
import com.teamgreen.pollconapp.entities.Vehicle;
import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "regController")
@RequestScoped
public class RegistrationController implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{GreenAppService}")
    GreenAppService greenAppService;
    private Map<String, String> vehicles = null;
    private Map<String, String> regNumbers = null;
    private String selectedVehicleNumber; // this is unique
    private String selectedAadharNumber; // treating this a unique for POC
    private Owner owner;
    private boolean emissionTestPassed;
    private Registration registration;
    private String selectedRegNumber;
    private String reason;
    private String receipt;
    private String emissionCertificate;
    private Map<String, String> owners = null;
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private List<Owner> ownerList = new ArrayList<Owner>();
    private List<Incident> incList = new ArrayList<Incident>();
    private List<Registration> regList = new ArrayList<Registration>();
    private List<Test> testList = null;
    private boolean ownerRender = false;

    public RegistrationController()
    {
        vehicles = new HashMap<String, String>();
        regNumbers = new HashMap<String, String>();
        owners = new HashMap<String, String>();
        owner = new Owner();
        registration = new Registration();
    }

    public boolean isOwnerRender()
    {
        return ownerRender;
    }

    public void setOwnerRender(boolean ownerRender)
    {
        this.ownerRender = ownerRender;
    }

    public List<Test> getTestList()
    {
        return testList;
    }

    public void setTestList(List<Test> testList)
    {
        this.testList = testList;
    }

    public boolean isEmissionTestPassed()
    {
        return emissionTestPassed;
    }

    public void setEmissionTestPassed(boolean emissionTestPassed)
    {
        this.emissionTestPassed = emissionTestPassed;
    }

    public String getEmissionCertificate()
    {
        return emissionCertificate;
    }

    public void setEmissionCertificate(String emissionCertificate)
    {
        this.emissionCertificate = emissionCertificate;
    }

    public String getReceipt()
    {
        return receipt;
    }

    public void setReceipt(String receipt)
    {
        this.receipt = receipt;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getSelectedRegNumber()
    {
        return selectedRegNumber;
    }

    public void setSelectedRegNumber(String selectedRegNumber)
    {
        this.selectedRegNumber = selectedRegNumber;
    }

    public Registration getRegistration()
    {
        return registration;
    }

    public void setRegistration(Registration registration)
    {
        this.registration = registration;
    }

    public Owner getOwner()
    {
        return owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;
    }

    public String getSelectedAadharNumber()
    {
        return selectedAadharNumber;
    }

    public void setSelectedAadharNumber(String selectedAadharNumber)
    {
        this.selectedAadharNumber = selectedAadharNumber;
    }

    public List<Incident> getIncList()
    {
        return incList;
    }

    public void setIncList(List<Incident> incList)
    {
        this.incList = incList;
    }

    public List<Registration> getRegList()
    {
        return regList;
    }

    public void setRegList(List<Registration> regList)
    {
        this.regList = regList;
    }

    public List<Owner> getOwnerList()
    {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList)
    {
        this.ownerList = ownerList;
    }

    public List<Vehicle> getVehicleList()
    {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
    }

    public Map<String, String> getVehicles()
    {
        return vehicles;
    }

    public void setVehicles(Map<String, String> vehicles)
    {
        this.vehicles = vehicles;
    }

    public Map<String, String> getOwners()
    {
        return owners;
    }

    public void setOwners(Map<String, String> owners)
    {
        this.owners = owners;
    }

    public String getSelectedVehicleNumber()
    {
        return selectedVehicleNumber;
    }

    public void setSelectedVehicleNumber(String selectedVehicleNumber)
    {
        this.selectedVehicleNumber = selectedVehicleNumber;
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

        for (Vehicle v : vehicleList)
        {
            if (!v.getTestStatus().equalsIgnoreCase("Failed"))
            {
                vehicles.put(v.getId() + "_" + v.getMake() + "_" + v.getModel() + "_" + v.getMakeDate() + "_" + v.getVehicleNumber(), v.getVehicleNumber());
            }
        }

        ownerList = getGreenAppService().getAllOwners();

        if (ownerList != null)
        {
            for (Owner o : ownerList)
            {
                owners.put(o.getId() + "_" + o.getAadharNumber() + "_" + o.getEmail() + "_" + o.getContactNumber(), o.getAadharNumber());
            }
        }

        regList = getGreenAppService().getAllRegistrations();

        incList = getGreenAppService().getAllIncidents();

        if (regList != null)
        {
            for (Registration rr : regList)
            {
                regNumbers.put(rr.getId() + "_" + rr.getOwner().getAadharNumber() + "_" + rr.getOwner().getName() + "_" + rr.getVehicle().getVehicleNumber() + "_" + rr.getVehicle().getMake(), rr.getRegNumber());
            }
        }
    }

    public Map<String, String> getRegNumbers()
    {
        return regNumbers;
    }

    public void setRegNumbers(Map<String, String> regNumbers)
    {
        this.regNumbers = regNumbers;
    }

    public String createRegistration()
    {
        if (getSelectedAadharNumber().isEmpty())
        {
            JSFUtils.addErrorMsg("No Vehicle Owner selected. Please fill the below form and submit if the Owner is not available in the database");

            return null;
        }
        else
        {
            try
            {
                String newRegistrationNumber = getGreenAppService().createRegistration(getSelectedAadharNumber(), getSelectedVehicleNumber(), getRegistration());
                JSFUtils.addInfoMsg("Vehicle Owner registered(linked) with the new vehicle successfully.");
                JSFUtils.addInfoMsg("Registration Number: " + newRegistrationNumber);
            }
            catch (Exception e)
            {
                JSFUtils.addErrorMsg("Error occurred. Cannot create registration.");
            }
        }

        return null;
    }

    public String addVehicleOwnerAndCreateRegistration()
    {
        try
        {
            String newRegistrationNumber = getGreenAppService().createRegistration(getOwner(), getSelectedVehicleNumber(), getRegistration());
            List<Owner> ownerList = getGreenAppService().getAllOwners();
            JSFUtils.addInfoMsg("New Owner added successfully and registration completed...");
            JSFUtils.addInfoMsg("Registration Number: " + newRegistrationNumber);
            setOwnerList(ownerList);
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. Cannot save Owner details...");
        }

        return null;
    }

    public String createIncident()
    {
        try
        {
            getGreenAppService().createIncident(getSelectedRegNumber(), getReason());

            List<Incident> incList = getGreenAppService().getAllIncidents();
            JSFUtils.addInfoMsg("Incident created successfully...");
            setIncList(incList);
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. Cannot save Incident details...");
        }

        return null;
    }

    public String generateReceipt()
    {
        try
        {
            List<Incident> incList = getGreenAppService().generateReceipt(getSelectedRegNumber());

            StringBuilder sb = new StringBuilder();

            for (Incident inc : incList)
            {
                sb.append(inc.getReason()).append("\n").append("\n");
            }

            setReceipt(sb.toString());

            JSFUtils.addInfoMsg("Receipt generated successfully...");
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. No Receipt..." + e);
        }

        return null;
    }

    public String generateCertificate()
    {
        try
        {
            boolean testPassed = getGreenAppService().isEmissionTestPassed(getSelectedRegNumber());

            if (testPassed)
            {
                setEmissionCertificate("Test Passed");
                setEmissionTestPassed(true);
            }
            else
            {
                setEmissionCertificate("Test Failed");
                setEmissionTestPassed(false);
            }

            JSFUtils.addInfoMsg("Certificate generated successfully...");
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred.");
        }

        return null;
    }

    public String getTestDetails()
    {
        try
        {
            List<Test> testDetails = getGreenAppService().getTestDetails(getSelectedRegNumber());

            if ((testDetails != null) && !testDetails.isEmpty())
            {
                JSFUtils.addInfoMsg("Success");
                setTestList(testDetails);
                setOwnerRender(true);
            }
            else
            {
                JSFUtils.addInfoMsg("No details found.");
                setOwnerRender(false);
            }
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred.");
        }

        return null;
    }
}
