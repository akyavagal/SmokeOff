package com.teamgreen.pollconapp.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public final class JSFUtils {
	public static void setLoggedinUser(String userId) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("LOGIN_USER", userId);
	}

	public static String getLoggedinUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);

		return (String) session.getAttribute("LOGIN_USER");
	}

	public static void addInfoMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErrorMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public static void addInfoMsg(String componentId, String msg) {
		FacesContext.getCurrentInstance().addMessage(componentId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErrorMsg(String componentId, String msg) {
		FacesContext.getCurrentInstance().addMessage(componentId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}
}
