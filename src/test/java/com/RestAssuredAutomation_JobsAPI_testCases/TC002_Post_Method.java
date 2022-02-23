package com.RestAssuredAutomation_JobsAPI_testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.RestAssuredAutomation_JobsAPI_base.Test_Base_JobsAPI;
import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public  class TC002_Post_Method extends Test_Base_JobsAPI{


	@BeforeClass
	public void Test_01_Base()
	{
		baseURI=JobsAPI_URI;
		basePath=Get_all_Job;
	}

	@Test(dataProvider="jobsapidata",priority=1)
	public void Test01_Post_validdata(String ejobid,String ejobtitle,String ejobloc,String ejobcomp,String ejobtype,String ejobposttime,String ejobdesc)
	{

		logger.info("***Verifying Post Method Response when valid data  is passed****");
		basePath=Get_all_Job;

		System.out.println(ejobtitle);
		Response response=

				given()
				.queryParam("Job Id",ejobid)
				.queryParam("Job Title", ejobtitle)
				.queryParam("Job Location", ejobloc)
				.queryParam("Job Company Name", ejobcomp)
				.queryParam("Job Type", ejobtype)
				.queryParam("Job Posted time", ejobposttime)
				.queryParam("Job Description", ejobdesc)
				.when()
				.post()  
				.then()
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.log().all()
				.extract().response();
		String JsonString=response.asString();

		Assert.assertEquals(JsonString.contains("5121"), true);
		Assert.assertEquals(JsonString.contains( ejobtitle), true);



	}
	@Test(priority=2 ,dependsOnMethods="Test01_Post_validdata")
	public void Test02_Post_with_ExistingID()
	{
		logger.info("***Verifying Post Method Response with ExistingID****");
		Response response=
				given()
				.queryParam("Job Id", existingJobId)
				.queryParam("Job Title", JobTitle)
				.queryParam("Job Location",  JobLocation)
				.queryParam("Job Company Name", JobCompanyName)
				.queryParam("Job Type", JobType)
				.queryParam("Job Posted time",JobPostedtime)
				.queryParam("Job Description", JobDescription)
				.when()
				.post()  
				.then()
				.statusCode(409)
				.statusLine("HTTP/1.1 409 CONFLICT")
				.log().all()
				.extract().response();
		String JsonString=response.asString();

		Assert.assertEquals(JsonString.contains("'"+existingJobId+"'"+" already exists."), true);
		//Assert.assertEquals(response.jsonPath().get("message"),"'5121' already exists.");
	}


	@DataProvider(name="jobsapidata")
	String[][] jobsData() throws IOException
	{
		//Read Data from Excel

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestData_JobsApi.xlsx";
		int rowNum=XLUtils.getRowCount(path,"Sheet1");
		int colCount=XLUtils.getCellCount(path, "Sheet1", 1);
		String jobsData[][]=new String [rowNum][colCount];

		for(int i=1;i<=rowNum;i++)
		{
			for (int j=0;j<colCount;j++)
			{
				jobsData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return (jobsData);
	}
























}
