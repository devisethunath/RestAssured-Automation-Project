package com.RestAssuredAutomation_LMSAPI_base;

import static io.restassured.RestAssured.baseURI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.RestAssuredAutomation_JobsAPI_utilities.ReadConfig;

public class Test_Base_LMSAPI {

ReadConfig readconfig=new ReadConfig();

	public Logger logger;
	
	public  String username=readconfig.username();  //"admin";
	public  String password=readconfig.password();  //"password";
	public  String invalid_username=readconfig.invalidUsername();  // "admi";
	public  String invalid_pwd=readconfig.InvalidPassword() ; //"passwor";
	
	public  String LmsAPI_URI= readconfig.LMSAPIUri();  //"https://lms-admin-rest-service.herokuapp.com";
	public  String get_all_programs=readconfig.LMSgetAllPgms();  //"/programs";
	public  String invalid_basepath=readconfig.InvalidBasePath();  //"/prorams";
	public  String non_existing_pgmId=readconfig.LmsNonExistPgmID();  //	"https://lms-admin-rest-service.herokuapp.com/programs/34989";

	public String valonline= readconfig.LMSvalonline();  //"true";
	public  String valpgmdesc=readconfig.LMSvalpgmdesc();  //"RestAssured";
	public  String valpgmname=readconfig.LMSValpgmname();  //"Automation Testing";

	public  String put_URI_Nonexisting=readconfig.LmsPutNonExist();  //"https://lms-admin-rest-service.herokuapp.com/programs/32";
	public  String delete_URI_nonExisting=  readconfig.LmsDeleteNonExist();  //"https://lms-admin-rest-service.herokuapp.com/programs/2829";

	
	@BeforeClass
	public void setup()
	{
		logger=Logger.getLogger("LMSAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
