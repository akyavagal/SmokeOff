package com.teamgreen.pollconapp.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.teamgreen.pollconapp.entities.RTO;
import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;

@ManagedBean(name = "adminController")
@RequestScoped
public class AdminController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{GreenAppService}")
	GreenAppService greenAppService;

	
	private List<RTO> rtoList = null;
	
	private RTO rto;
	

	public List<RTO> getRtoList() {
		return rtoList;
	}


	public void setRtoList(List<RTO> rtoList) {
		this.rtoList = rtoList;
	}


	public RTO getRto() {
		return rto;
	}


	public void setRto(RTO rto) {
		this.rto = rto;
	}


	public AdminController() {
		rto = new RTO();
	}

	
	public GreenAppService getGreenAppService() {
		return greenAppService;
	}

	


	public void setGreenAppService(GreenAppService greenAppService) {
		this.greenAppService = greenAppService;
	}

	@PostConstruct
	public void init() {
		rtoList = getGreenAppService().getAllRTOs();
	}

	
	public String addRTO() {
	 	
	 	try{
		 	getGreenAppService().createRegistration(getRto());
		 	List<RTO> rtoList = getGreenAppService().getAllRTOs();
		 	JSFUtils.addInfoMsg("New RTO details saved successfully..");
		 	setRtoList(rtoList);
	 	}
	 	catch(Exception e){
	 		JSFUtils.addErrorMsg("Error occurred. Cannot save RTO...");
	 	}
	 	return null;
	 }

	
	
}
