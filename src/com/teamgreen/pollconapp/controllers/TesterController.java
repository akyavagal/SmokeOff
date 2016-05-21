package com.teamgreen.pollconapp.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.teamgreen.pollconapp.entities.Test;
import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;


@ManagedBean(name = "testerController")
@RequestScoped
public class TesterController implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{GreenAppService}")
    GreenAppService greenAppService;
    private List<Test> testList = null;
    private List<Test> testListExpiring = null;
    public List<Test> getTestListExpiring() {
		return testListExpiring;
	}

	public void setTestListExpiring(List<Test> testListExpiring) {
		this.testListExpiring = testListExpiring;
	}


	private Test test;
    private String selectedRegNumber;

    public TesterController()
    {
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
        testList = getGreenAppService().getAllTests();
        testListExpiring = getGreenAppService().getAllTestsExpiringSoon();
    }


    public String testEmission()
    {
        try
        {
            String testResult = getGreenAppService().testEmission(getTest(), getSelectedRegNumber());
            getTest().setTestResult(testResult);

            if (testResult.equalsIgnoreCase("Passed"))
            {
                getGreenAppService().createTest(getTest(), getSelectedRegNumber());
            }
            else
            {
                getGreenAppService().createTest(getTest(), getSelectedRegNumber());
                getGreenAppService().createIncident(getSelectedRegNumber(), "Emission Test has failed.");
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
