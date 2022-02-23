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


public class TC005_PUT_Request extends Test_Base_LMSAPI {

	@Test(dataProvider="LMSApidataPut")
	public void Test01_PUTRequest_ExistingProgramID(String epgmid,String eonline,String epgmdesc,String epgmname)   //Can be verified in postman using single programId

	{
		logger.info("*****Verifying PUT Method  Response Details with existing ProgramId********");

		String uri=epgmid.replaceAll("\"", "");
		String url=LmsAPI_URI+get_all_programs+"/"+uri;
		System.out.println(url);

		JSONObject request=new JSONObject();
		request.put("online",  eonline);
		request.put("programDescription",  epgmdesc);
		request.put("programName", epgmname);

		Response res=
				given()
				.auth().basic(username, password)
				.contentType("application/json")
				.body(request.toJSONString())
				.when()
				.put(url)
				.then()
				.statusCode(200)
				.log().all()
				.body("programId",equalTo(epgmid))
				.body("programName", equalTo(epgmname))
				.body("programDescription", equalTo(epgmdesc))
				.extract().response();

		String JsonString=res.asString();

	}

	@Test
	public void Test02_PUTRequest_nonExistingProgramID()

	{
		logger.info("*****Verifying PUT Method  Response Details with non existing ProgramId********");

		JSONObject request=new JSONObject();
		//request.put("programId",nonexistingpgmId);
		request.put("online", valonline);
		request.put("programDescription", valpgmdesc);
		request.put("programName",valpgmname);

		given()
		.auth().basic(username, password)
		.contentType("application/json")
		.body(request.toJSONString())
		.when()
		.put(put_URI_Nonexisting)
		.then()
		.statusCode(404)
		.headers("Content-Type", "application/json")
		.statusLine("HTTP/1.1 404 NOT FOUND")
		.log().all();

	}

	@DataProvider(name="LMSApidataPut")

	String [][]getLMSApidata() throws IOException
	{

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestDataLMSApi.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet3");
		int colcount=XLUtils.getCellCount(path, "Sheet3", 1);

		String getLMSApidataPut[][]=new String [rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				getLMSApidataPut[i-1][j]=XLUtils.getCellData(path, "Sheet3", i, j);
				System.out.println(getLMSApidataPut[i-1][j]);
			}
		}

		return(getLMSApidataPut);
	}

}


