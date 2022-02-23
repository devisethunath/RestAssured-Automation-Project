package com.RestAssuredAutomation_JobsAPI_testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.RestAssuredAutomation_JobsAPI_base.Test_Base_JobsAPI;
import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;

import io.restassured.response.Response;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.HashMap;

public class TC003_PUT_Method extends Test_Base_JobsAPI{

	@BeforeClass
	public void Test_01_Base()
	{
		baseURI=JobsAPI_URI;
		basePath=Get_all_Job;
	}


	@Test(dataProvider="jobsapiputdata",priority=3)
	public void Test01_PUT_ValidData(String ejobid1,String ejobtitle1)
	{
		logger.info("***Verifying PUT Method Response with Valid Data****");
		Response res=
				given()
				.queryParam("Job Id" ,ejobid1)    
				.queryParam("Job Title", ejobtitle1)   
				.when()  
				.put()
				.then()  
				.statusCode(200)
				.log().all()
				.extract().response();
		String JsonString=res.asString();
		Assert.assertEquals(JsonString.contains(ejobid1),true);
		Assert.assertEquals(JsonString.contains(ejobtitle1),true);

	}


	@Test(priority=4)
	public void Test02_PUT_NonExistingJobID()
	{logger.info("***Verifying PUT Method Response with NonExisting JobID***");

	Response res=
			given()
			.queryParam("Job Id", nonExistingJobId)
			.queryParam("Job Title", JobTitle)
			.when()  
			.put()
			.then()  
			.statusCode(404)
			.headers("Content-Type", "application/json")
			.statusLine("HTTP/1.1 404 NOT FOUND")
			.log().all()
			.extract().response();

	String JsonString=res.asString();
	Assert.assertEquals(JsonString.contains("'"+nonExistingJobId+"'"+" job not found."), true);
	}

	@DataProvider(name="jobsapiputdata")
	String[][] jobsDataPut() throws IOException
	{
		//Read Data from Excel

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestData_JobsApi.xlsx";
		//DataFormatter formatter= new DataFormatter();
		int rowNum=XLUtils.getRowCount(path,"Sheet2");
		int colCount=XLUtils.getCellCount(path, "Sheet2", 1);
		String jobsDataPut[][]=new String [rowNum][colCount];

		for(int i=1;i<=rowNum;i++)
		{
			for (int j=0;j<colCount;j++)
			{
				jobsDataPut[i-1][j]=XLUtils.getCellData(path, "Sheet2", i, j);
				System.out.println(jobsDataPut[i-1][j]);
			}
		}
		return (jobsDataPut);
	}

}

