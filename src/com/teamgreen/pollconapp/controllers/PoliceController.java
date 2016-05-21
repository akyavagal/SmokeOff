package com.teamgreen.pollconapp.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;


@ManagedBean(name = "policeController")
@RequestScoped
public class PoliceController implements Serializable
{
	private MapModel simpleModel;
	
    private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{GreenAppService}")
    GreenAppService greenAppService;
	public GreenAppService getGreenAppService() {
		return greenAppService;
	}
	public void setGreenAppService(GreenAppService greenAppService) {
		this.greenAppService = greenAppService;
	}
	
	 @PostConstruct
	    public void init() {
	        simpleModel = new DefaultMapModel();
	          
	        //Shared coordinates
	        LatLng coord1 = new LatLng(12.954517, 77.3007328);
	        LatLng coord2 = new LatLng(12.944517, 77.3107328);
	        LatLng coord3 = new LatLng(12.934517, 77.3307328);
	        LatLng coord4 = new LatLng(12.924517, 77.3807328);
	          
	        //Basic marker
	        simpleModel.addOverlay(new Marker(coord1, "KA01 JJ655"));
	        simpleModel.addOverlay(new Marker(coord2, "KA04 JI688"));
	        simpleModel.addOverlay(new Marker(coord3, "KA02 MM659"));
	        simpleModel.addOverlay(new Marker(coord4, "KA05 PL787"));
	    }
	public MapModel getSimpleModel() {
		return simpleModel;
	}
	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}
}