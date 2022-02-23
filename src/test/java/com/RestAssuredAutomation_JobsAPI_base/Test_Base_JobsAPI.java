package com.RestAssuredAutomation_JobsAPI_base;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.RestAssuredAutomation_JobsAPI_utilities.ReadConfig;

public class Test_Base_JobsAPI {

	ReadConfig readconfig=new ReadConfig();
	

	public Logger logger;
	public  String JobsAPI_URI= readconfig.getJobsAPIURL() ;                //"https://numpyninja-joblistapi.herokuapp.com";
	public  String	Get_all_Job= readconfig.getPathParamJobsAPI();         //     "/Jobs";
	public  String Invalid_get_param= readconfig.invalidGetPathParamJobsapi();  //"/J";
	
	public  String existingJobId=readconfig.existingJobId();              //"7890"; //Existing in Database
	public  String JobTitle=readconfig.existingJobId();                   //"SDET";
	public String JobLocation= readconfig.joblocation();                 //"Bentonville,AR";
	public String JobCompanyName= readconfig.JobCompanyName();           //"Infosys";
	public  String JobType= readconfig.JobType();                        //"Permanent";
	public  String JobPostedtime=readconfig.JobPostedTime();             //"4 mins ago";
	public String JobDescription=readconfig.JobDescription();           // "XYZ";
	
	public String nonExistingJobId=readconfig.nonExistingJobId();       //"23";
	

	@BeforeClass()
	public void setup()
	{
		logger=Logger.getLogger("JobsAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);

	}
	
}

