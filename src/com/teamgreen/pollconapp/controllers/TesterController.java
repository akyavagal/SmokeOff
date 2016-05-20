package com.teamgreen.pollconapp.controllers;

import com.teamgreen.pollconapp.entities.EmissionTestCenter;
import com.teamgreen.pollconapp.entities.Test;
import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "testerController")
@RequestScoped
public class TesterController implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{GreenAppService}")
    GreenAppService greenAppService;
    private List<EmissionTestCenter> testerList = null;
    private List<Test> testList = null;
    private Test test;
    private String selectedRegNumber;
    private EmissionTestCenter tester;

    public TesterController()
    {
        tester = new EmissionTestCenter();
        test = new Test();
    }

    public Test getTest()
    {
        return test;
    }

    public void setTest(Test test)
    {
        this.test = test;
    }

    public String getSelectedRegNumber()
    {
        return selectedRegNumber;
    }

    public void setSelectedRegNumber(String selectedRegNumber)
    {
        this.selectedRegNumber = selectedRegNumber;
    }

    public List<Test> getTestList()
    {
        return testList;
    }

    public void setTestList(List<Test> testList)
    {
        this.testList = testList;
    }

    public List<EmissionTestCenter> getTesterList()
    {
        return testerList;
    }

    public void setTesterList(List<EmissionTestCenter> testerList)
    {
        this.testerList = testerList;
    }

    public EmissionTestCenter getTester()
    {
        return tester;
    }

    public void setTester(EmissionTestCenter tester)
    {
        this.tester = tester;
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
        testerList = getGreenAppService().getAllTesters();
        testList = getGreenAppService().getAllTests();
    }

    public String addTester()
    {
        try
        {
            getGreenAppService().createRegistration(getTester());

            List<EmissionTestCenter> testerList = getGreenAppService().getAllTesters();
            JSFUtils.addInfoMsg("New Tester details saved successfully..");
            setTesterList(testerList);
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. Cannot save Tester...");
        }

        return null;
    }

    public String testEmission()
    {
        try
        {
            String testResult = getGreenAppService().testEmission(getTest(), getSelectedRegNumber());
            getTest().setTestResult(testResult);

            if (testResult.equalsIgnoreCase("Test Pass"))
            {
                getGreenAppService().createTest(getTest(), getSelectedRegNumber());
            }
            else
            {
                getGreenAppService().createTest(getTest(), getSelectedRegNumber());
                getGreenAppService().createIncident(getSelectedRegNumber(), "Emission Test has failed. Please invalidate the registration.");
            }

            JSFUtils.addInfoMsg("Emission Test Result: " + testResult);
        }
        catch (Exception e)
        {
            JSFUtils.addErrorMsg("Error occurred. Cannot save Tester...");
        }

        return null;
    }
}
