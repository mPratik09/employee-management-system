package com.employee.management.system.config;

public class DepartmentContext
{

	private String landingPage;
	private String allowedView;

	public String getLandingPage()
	{
		return landingPage;
	}

	public void setLandingPage(String landingPage)
	{
		this.landingPage = landingPage;
	}

	public String getAllowedView()
	{
		return allowedView;
	}

	public void setAllowedView(String allowedView)
	{
		this.allowedView = allowedView;
	}

	@Override
	public String toString()
	{
		return "DepartmentContext [landingPage=" + landingPage + ", allowedView=" + allowedView + "]";
	}

}
