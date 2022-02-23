package com.RestAssuredAutomation_JobsAPI_utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {

	Properties pro;

	public ReadConfig()
	{
		File src= new File("./Configuration/config.properties");
		try {FileInputStream fis = new FileInputStream(src) ;
		pro= new Properties();
		pro.load(fis);

		} 
		catch (Exception e) {
			System.out.print("exception is" +e.getMessage());
		} 


	}

	public String getJobsAPIURL()
	{
		String jobsapiurl=pro.getProperty("JobsAPIURI");
		return jobsapiurl;
	}

	public String getPathParamJobsAPI()
	{
		String pathParamJobsApi=pro.getProperty("Get_all_Job");
		return pathParamJobsApi;
	}
	public String invalidGetPathParamJobsapi()
	{
		String invalidParamJobsApi=pro.getProperty("Invalid_get_param");
		return  invalidParamJobsApi;
	}
	
	public String existingJobId()
	{
		String existingJobId=pro.getProperty("existingJobId");
		return existingJobId;
	}
	
	public String  JobTitle()
	{
		String jobTitle=pro.getProperty("JobTitle");
		return jobTitle;
	}
	public String joblocation()
	{
		String joblocation=pro.getProperty("JobLocation");
		return joblocation;
	}
	public String JobCompanyName()
	{
		String jobcompanyname=pro.getProperty("JobCompanyName");
		return jobcompanyname;
	}
	public String JobType()
	{
		String jobtype=pro.getProperty("JobType");
		return jobtype;
	}
	
	public String JobPostedTime()
	{
		String jobpostedtime=pro.getProperty("JobPostedtime");
		return jobpostedtime;
	}
	public String JobDescription()
	{
		String jobdesc=pro.getProperty("JobDescription");
		return jobdesc;
	}
	
	public String nonExistingJobId()
	{
		String nonexistjobid=pro.getProperty("nonExistingJobId");
		return nonexistjobid;
	}
	public String username()
	{
		String Username=pro.getProperty("username");
		return Username;
	}
	public String password()
	{
		String Password=pro.getProperty("password");
		return Password;
	}
	public String invalidUsername()
	{
		String InvalidUsername=pro.getProperty("invalid_username");
		return InvalidUsername;
	}
	public String InvalidPassword()
	{
		String invalidpwd=pro.getProperty("invalid_pwd");
		return invalidpwd;
	}
	
	public String LMSAPIUri()
	{
		String LmsApiUri=pro.getProperty("LmsAPI_URI");
		return  LmsApiUri;
	}
	
	public String LMSgetAllPgms()
	{
		String getallPgms=pro.getProperty("get_all_programs");
		return getallPgms;
	}
	
	public String InvalidBasePath()
	{
		String invalidBasePath=pro.getProperty("invalid_basepath");
		return invalidBasePath;
	}
	public String LmsNonExistPgmID()
	{
		String nonexistpgmid=pro.getProperty("non_existing_pgmId");
		return nonexistpgmid;
	}
	public String LMSvalonline()
	{
		String valOnline=pro.getProperty("valonline");
		return valOnline;
	}
	public String LMSvalpgmdesc()
	{
		String valpgmdesc=pro.getProperty("valpgmdesc");
		return valpgmdesc;
	}
	
	public String LMSValpgmname()
	{
		String valpgmname=pro.getProperty("valpgmname");
		return valpgmname;
	}
	public String LmsPutNonExist()
	{
		String putUriNonExist=pro.getProperty("put_URI_Nonexisting");
		return putUriNonExist;
	}
	
	public String LmsDeleteNonExist()
	{
		String LmsDeleteNonExist=pro.getProperty("delete_URI_nonExisting");
		return LmsDeleteNonExist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
