package com.teamgreen.pollconapp.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.teamgreen.pollconapp.services.GreenAppService;
import com.teamgreen.pollconapp.utils.JSFUtils;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{GreenAppService}")
	GreenAppService greenAppService;

	private boolean userLoggedIn;

	private String role;

	private Map<String, String> roles;

	private String userId;

	private String password;

	public UserController() {

	}

	public Map<String, String> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, String> roles) {
		this.roles = roles;
	}

	
	public GreenAppService getGreenAppService() {
		return greenAppService;
	}

	public void setGreenAppService(GreenAppService greenAppService) {
		this.greenAppService = greenAppService;
	}

	public String doLogin() {

		String role = getRole();
		String userId = getUserId();
		String password = getPassword();

		boolean authenticated = getGreenAppService().authenticateUser(role, userId, password);

		if (authenticated) {
			setUserLoggedIn(true);
			 JSFUtils.setLoggedinUser(userId);
			
			if (role.equalsIgnoreCase("RTO"))
			{
				return "rto.jsf?faces-redirect=true";
			}
			else if (role.equalsIgnoreCase("Vehicle Owner"))
			{
				return "owner.jsf?faces-redirect=true";
			}
			else if (role.equalsIgnoreCase("Testing Center"))
			{
				return "tester.jsf?faces-redirect=true";
			}
			else if (role.equalsIgnoreCase("Site Admin"))
			{
				return "admin.jsf?faces-redirect=true";
			}
			else if (role.equalsIgnoreCase("Vehicle Maker"))
			{
				return "vendor.jsf?faces-redirect=true";
			}
		} else {
			JSFUtils.addErrorMsg("Invalid UserType / UserId / Password combination entered.");
		}

		return null;
	}


	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.removeAttribute("LOGIN_USER");
		session.invalidate();
		
	        
	    JSFUtils.addInfoMsg("You have been logged out. Bye!");

		return "login.jsf?faces-redirect=true";
	}

	public Map<String, Boolean> getHasMessages() {
		HashMap<String, Boolean> flags = new HashMap<String, Boolean>(1);
		Iterator<String> messages = FacesContext.getCurrentInstance().getClientIdsWithMessages();

		while (messages.hasNext()) {
			flags.put(messages.next(), Boolean.TRUE);
		}

		return flags;
	}

	

	public boolean isUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(boolean userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

	@PostConstruct
	public void init() {
		roles  = new HashMap<String, String>();
        roles.put("RTO", "RTO");
        roles.put("Vehicle Owner", "Vehicle Owner");
        roles.put("Vehicle Maker", "Vehicle Maker");
        roles.put("Testing Center", "Testing Center");
        roles.put("Site Admin", "Site Admin");  
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
