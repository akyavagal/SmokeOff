package com.teamgreen.pollconapp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		System.out.println("In filter config.....");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestURI = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		String requestPath = requestURI.substring(contextPath.length()+1);
		String loggedinUser = (String) httpRequest.getSession().getAttribute("LOGIN_USER");
		System.out.println("Logged In User ===> "+loggedinUser);
		
		if (!isPublicUrl(requestPath) && loggedinUser == null)
		{
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login.jsf");
		} 
		else 
		{
			chain.doFilter(request, response);
		}
		
	}
	
	private boolean isPublicUrl(String url){
		return 	url.startsWith("views/login.jsf") 
				|| url.startsWith("javax.faces.resource");
	}
	
	@Override
	public void destroy()
	{
	}

}
