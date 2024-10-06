package com.example.assessment.vl_test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddNewDeviceTest {

	static  {
		RestAssured.baseURI="https://api.restful-api.dev/objects";
		
	}
	
	@Test
	public void addNewDeviceTest() {
		
		JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Apple Max Pro 1TB");
		
		JSONObject data = new JSONObject();
        data.put("year", 2023);
        data.put("price", 7999.99);
        data.put("CPU model", "Apple ARM A7");
        data.put("Hard disk size", "1 TB");
		
		requestBody.put("data", data);
		
		Response response = given().header("Content-Type", "application/json").body(requestBody.toString()).post().then().extract().response();
		
		System.out.println("Response Status Code :" + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		String id = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("createdAt");
		
		String name = response.jsonPath().getString("name");
		int year = response.jsonPath().getInt("data.year");
        double price = response.jsonPath().getDouble("data.price");
		
		String cpuModel = response.jsonPath().getString("data['CPU model']");
        String hardDisk = response.jsonPath().getString("data['Hard disk size']");
		
        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertNotNull(createdAt, "createdAt should not be null");
		
		Assert.assertEquals(name, "Apple Max Pro 1TB");
        Assert.assertEquals(year, 2023);
        Assert.assertEquals(price, 7999.99);
        Assert.assertEquals(cpuModel, "Apple ARM A7");
        Assert.assertEquals(hardDisk, "1 TB");
		
		System.out.println("Test Passed");
		
		
		
	}

    }

