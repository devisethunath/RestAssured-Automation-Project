package com.RestAssuredAutomation_LMSAPI_testCases;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.RestAssuredAutomation_JobsAPI_base.Test_Base_JobsAPI;
import com.RestAssuredAutomation_LMSAPI_base.Test_Base_LMSAPI;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC001_Check_Authentication extends Test_Base_LMSAPI{


	@BeforeClass
	public void Test_01_Base()
	{
		baseURI= LmsAPI_URI;
		basePath=get_all_programs;
	}
	@Test
	public void Test01_Valid_Credentials()
	{
		logger.info("***Verifying Authentication with valid credentials*****");

		given()
		.auth().basic(username, password)
		.when() 
		.get()
		.then()  
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ")
		.contentType("application/json")
		.log().all()
		.extract().response();

	}

	@Test
	public void Test02_Invalid_Credentials()
	{
		logger.info("****Verifying Get Method Response with invalid credentials****");

		given()
		.auth().basic(invalid_username, invalid_pwd)
		.when() 
		.get()
		.then()  
		.statusCode(401)
		.statusLine("HTTP/1.1 401 ")
		.log().all()
		.extract().response();
	}

	@Test
	public void Test03_NotPassing_Credentials()
	{
		logger.info("****Verifying Get Method  Response without  passing  credentials*********");

		given()
		.when() 
		.get()
		.then()  
		.statusCode(401)
		.statusLine("HTTP/1.1 401 ")
		.body( "error",equalTo ("Unauthorized"))
		.log().all()
		.extract().response();
	}



}
