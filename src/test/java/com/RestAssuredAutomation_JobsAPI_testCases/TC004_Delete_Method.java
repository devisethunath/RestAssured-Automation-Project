package com.RestAssuredAutomation_JobsAPI_testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.RestAssuredAutomation_JobsAPI_base.Test_Base_JobsAPI;
import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;

import io.restassured.response.Response;

import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.HashMap;

public class TC004_Delete_Method extends Test_Base_JobsAPI{
	@BeforeClass
	public void Test_01_Base()
	{
		baseURI=JobsAPI_URI;
		basePath=Get_all_Job;
	}

	@Test(dataProvider="jobsapideletedata",priority=5)
	public void Test01_Delete_ExistingJobId(String ejobid2)
	{
		logger.info("***Verifying Delete  Method Response with existing   JobID***");
		Response res=
				given()
				.queryParam("Job Id",  ejobid2)
				.when() 
				.delete()
				.then()  
				.statusCode(200)
				.log().all()
				.extract().response();
		String JsonString=res.asString();

	}

	@Test(priority=6)
	public void Test02_Delete_nonExistingJobId()
	{
		logger.info("***Verifying Delete  Method Response with non existing   JobID***");
		Response res=
				given()
				.queryParam("Job Id", nonExistingJobId)
				.when() 
				.delete()
				.then()  
				.statusCode(404)
				.extract().response();

		String JsonString=res.asString();
		Assert.assertEquals(JsonString.contains("'"+nonExistingJobId+"'"+" Job  not found."), true);
	}

	@DataProvider(name="jobsapideletedata")
	String[][] jobsDataDelete() throws IOException

	{
		//Read Data from Excel

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestData_JobsApi.xlsx";
		//DataFormatter formatter= new DataFormatter();
		int rowNum=XLUtils.getRowCount(path,"Sheet3");
		int colCount=XLUtils.getCellCount(path, "Sheet3", 1);
		String jobsDataDelete[][]=new String [rowNum][colCount];

		for(int i=1;i<=rowNum;i++)
		{
			for (int j=0;j<colCount;j++)
			{
				jobsDataDelete[i-1][j]=XLUtils.getCellData(path, "Sheet2", i, j);
			}
		}
		return (jobsDataDelete);
	}

}
