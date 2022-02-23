package com.RestAssuredAutomation_LMSAPI_testCases;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssuredAutomation_LMSAPI_base.Test_Base_LMSAPI;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class TC002_Get_All_Details  extends Test_Base_LMSAPI {

	@BeforeClass
	public void Test_01_Base()
	{
		baseURI= LmsAPI_URI;
	}

	@Test
	public void Test_02_valIdRequestURL()
	{
		logger.info("***********Verifying Get Method Response Details with valid request URL*****************");

		basePath=get_all_programs;
		Response res=
				given()
				.auth().basic(username, password)
				.when() 
				.get()
				.then()  
				.statusCode(200)

				.assertThat().body(matchesJsonSchemaInClasspath("schemaLMS.json"))
				.statusLine("HTTP/1.1 200 ")
				.contentType("application/json")
				//.body("[0].programId",equalTo(4037))
				//.body("[0].programName", equalTo("DEMOname"))
				//.body("[0].programDescription",equalTo("DEMOid"))
				.log().all()
				.extract().response();
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Selenium CucumberBDD"), true);//5850
		Assert.assertEquals(jsonString.contains("5850"), true);//5850
	}

	@Test
	public void Test_03_invalIdRequestURL()
	{
		logger.info("***********Verifying Get Method  Response Details with Invalid request URL*****************");

		basePath=invalid_basepath;
		given()
		.auth().basic(username, password)
		.when()
		.get()
		.then()  
		.statusCode(404)
		.statusLine("HTTP/1.1 404 ")
		.body( "error",equalTo ("Not Found"))
		.log().all();

	}

}


