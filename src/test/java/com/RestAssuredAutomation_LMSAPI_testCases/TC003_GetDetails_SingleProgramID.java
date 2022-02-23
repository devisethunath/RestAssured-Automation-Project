package com.RestAssuredAutomation_LMSAPI_testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;
import com.RestAssuredAutomation_LMSAPI_base.Test_Base_LMSAPI;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.HashMap;


public class TC003_GetDetails_SingleProgramID extends Test_Base_LMSAPI{


	@Test(dataProvider="LMSApidataGet")
	public  void  Test03_GetDetails_singleProgramId(String epgmId)

	{
		logger.info("*******Verifying Get Method  Response Details for a single ProgramId*********");


		String epgmid=epgmId.replaceAll("\"", "");
		String URL="https://lms-admin-rest-service.herokuapp.com/programs/"+epgmid;

		Response res=
				given()
				.auth().basic(username, password)
				.when() 
				.get(URL)
				.then()  
				.statusCode(200)
				.statusLine("HTTP/1.1 200 ")
				.contentType("application/json")
				.log().all()
				.extract().response();

		Assert.assertEquals(res.jsonPath().get("programId"),Integer.parseInt(epgmid));
		Assert.assertEquals(res.jsonPath().get("programName"),"modified today");
		Assert.assertEquals(res.jsonPath().get("programDescription"),"modidied today");

		String JsonString=res.asString();
		Assert.assertEquals(JsonString.contains(epgmid), true);
		Assert.assertEquals(JsonString.contains("modified today"), true);
	}

	@Test
	public  void  Test02_GetDetails_NonExistingProgramId()

	{
		logger.info("*****Verifying Get Method Response Details for non existing ProgramId********");
		Response res=
				given()
				.auth().basic(username, password)
				.when() 
				.get(non_existing_pgmId)
				.then()  
				.statusCode(200)
				.statusLine("HTTP/1.1 200 ")
				.contentType("application/json")
				.log().all()
				.extract().response();
		String jsonstring=res.asString();
		Assert.assertEquals(jsonstring.contains("null"), true);
	}

	@DataProvider(name="LMSApidataGet")

	String [][]getLMSApidataGet() throws IOException
	{

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestDataLMSApi.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet2");
		int colcount=XLUtils.getCellCount(path, "Sheet2", 1);

		System.out.println(rownum+""+colcount);

		String getLMSApidataGet[][]=new String [rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				getLMSApidataGet[i-1][j]=XLUtils.getCellData(path, "Sheet2", i, j);
				System.out.println(getLMSApidataGet[i-1][j]);
			}
		}

		return(getLMSApidataGet);
	}}
