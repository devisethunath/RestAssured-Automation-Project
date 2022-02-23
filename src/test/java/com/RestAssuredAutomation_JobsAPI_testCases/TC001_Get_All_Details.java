package com.RestAssuredAutomation_JobsAPI_testCases;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.Assert;
//import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssuredAutomation_JobsAPI_base.Test_Base_JobsAPI;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.restassured.response.Response;

public class TC001_Get_All_Details extends Test_Base_JobsAPI {

	@BeforeClass
	public void Test_01_Base()
	{
		baseURI=JobsAPI_URI;
	}
	@Test

	public void Test_02_valIdRequestURL()
	{
		logger.info("***Verifying Get Method Response when valid URL is passed****");

		basePath=Get_all_Job;
		Response res=
				given()
				.when() 
				.get()
				.then()  
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.contentType("application/json")
				.log().all()
				.extract().response();

		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.statusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(res.contentType(),"application/json");


		String JsonString=res.asString();
		Assert.assertEquals(JsonString.contains("SDET Engineer"), true);
		Assert.assertEquals(JsonString.contains("Senior Software Developer in Test (SDET)"), true);

		assertThat( JsonString.replaceAll("NaN", "null") , matchesJsonSchemaInClasspath("schemaJobs.json"));
	}
	@Test
	public void Test_03_invalIdRequestURL()
	{
		logger.info("***Verifying Get Method Response when valid Invalid URL is passed****");

		basePath=Invalid_get_param;
		Response response=
				given()
				.when()
				.get()
				.then()  
				.statusCode(404)
				.statusLine("HTTP/1.1 404 NOT FOUND")
				.log().all()
				.extract().response();

		String JsonString=response.asString();
		Assert.assertEquals(JsonString.contains("The requested URL was not found on the server"),true);

	}

}
