package com.RestAssuredAutomation_LMSAPI_testCases;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;
import com.RestAssuredAutomation_LMSAPI_base.Test_Base_LMSAPI;

import io.restassured.response.Response;

import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.util.HashMap;

public class TC004_POST_Request extends Test_Base_LMSAPI{

	@BeforeClass
	public void Test_01_Base()
	{
		baseURI= LmsAPI_URI;
		basePath=get_all_programs;
	}

	@Test(dataProvider="LMSApidata")
	public void Test01_PostRequest_ValidData(String eonline,String epgmdesc,String epgmname)
	{
		logger.info("*****Verifying Post Method  Response Details with valid data********");

		JSONObject request=new JSONObject();
		request.put("online", eonline);
		request.put("programDescription",epgmdesc);
		request.put("programName", epgmname);
		Response res=
				given()
				.auth().basic(username, password)
				.contentType("application/json")
				.body(request.toJSONString())
				.when()
				.post()
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		String jsonstring=res.asString();


		Assert.assertEquals(res.jsonPath().get("programName"),epgmname);
		Assert.assertEquals(res.jsonPath().get("programDescription"),epgmdesc);
		Assert.assertEquals(jsonstring.contains(eonline),true);
	}


	@Test
	public void Test04_MandatoryParams_Missing()
	{
		logger.info("*****Verifying Post Method  Response Details when Mandatory params missing********");
		JSONObject request=new JSONObject();
		request.put("online", valonline);
		request.put("programDescription", valpgmdesc);
		//		request.put("programName", null);
		Response res=
				given()
				.auth().basic(username, password)
				.contentType("application/json")
				.body(request.toJSONString())
				.when()
				.post()
				.then()
				.statusCode(500)
				.log().all()
				.extract().response();
		String jsonstring=res.asString();
		Assert.assertEquals(jsonstring.contains( "Internal Server Error"),true);
	}


	@DataProvider(name="LMSApidata")

	String [][]getLMSApidata() throws IOException
	{

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestDataLMSApi.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);

		String getLMSApidata[][]=new String [rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				getLMSApidata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
				System.out.println(getLMSApidata[i-1][j]);
			}
		}

		return(getLMSApidata);
	}


}
