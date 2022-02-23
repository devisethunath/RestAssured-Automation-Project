package com.RestAssuredAutomation_LMSAPI_testCases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssuredAutomation_JobsAPI_utilities.XLUtils;
import com.RestAssuredAutomation_LMSAPI_base.Test_Base_LMSAPI;

import io.restassured.response.Response;

public class TC006_DELETE_Request extends Test_Base_LMSAPI{

	@Test(dataProvider="LMSApidatadelete")//Verify in postman using single id
	public void Test01_DeleteRequest_ExistingProgramID(String eiddelete)
	{
		logger.info("*****Verifying Delete  Method  Response Details with existing ProgramId********");

		String uri=eiddelete.replaceAll("\"", "");
		String url=LmsAPI_URI+get_all_programs+"/"+uri;
		System.out.println(url);

		given()
		.auth().basic(username, password)
		.contentType("application/json")
		.when()
		.delete(url)
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 ")
		.log().all();
	}

	@Test
	public void Test02_DeleteRequest_NonExistingProgramID() {

		logger.info("*****Verifying Delete  Method  Response Details with non existing ProgramId********");
		Response res=
				given()
				.auth().basic(username, password)
				.contentType("application/json")
				.when()
				.delete(delete_URI_nonExisting)
				.then()
				.statusCode(500)
				.log().all()
				.extract().response();
		String JsonResponse=res.asString();
		Assert.assertEquals(JsonResponse.contains("Internal Server Error"), true);
	}


	@DataProvider(name="LMSApidatadelete")

	String [][]getLMSApidatadelete() throws IOException
	{

		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\RestAssuredAutomation\\testData\\TestDataLMSApi.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet4");
		int colcount=XLUtils.getCellCount(path, "Sheet4", 1);

		String getLMSApidatadelete[][]=new String [rownum][colcount];

		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				getLMSApidatadelete[i-1][j]=XLUtils.getCellData(path, "Sheet4", i, j);
				System.out.println(getLMSApidatadelete[i-1][j]);
			}
		}

		return(getLMSApidatadelete);
	}
}
