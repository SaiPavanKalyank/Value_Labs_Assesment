package com.example.assessment.vl_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonTest {
	WebDriver driver;
	
	@Test
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/vl-test/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.get("https://www.amazon.com/");
			
			Thread.sleep(15000);
			
			WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
			searchBox.sendKeys("toys");
			 driver.findElement(By.id("nav-search-submit-button")).click();
			
			WebElement firstProduct = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[2]"));
			String firstProductSRPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[2]")).getText();
			firstProduct.click();
			String firstProductPDPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
			WebElement addToCart = driver.findElement(By.id("//input[@id='add-to-cart-button']"));
			addToCart.click();
			driver.navigate().back();
			driver.navigate().back();
			WebElement secondProduct = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[6]"));
			String secondProductSRPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[]")).getText();
			secondProduct.click();
			String secondProductPDPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
			addToCart.click();
			driver.findElement(By.xpath("//span[@class='nav-cart-icon nav-sprite']")).click();
			String firstProductCartPrice = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold'])[1]")).getText();
			String secondProductCartPrice = driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap sc-product-price a-text-bold'])[2]")).getText();
			
			Assert.assertTrue(firstProductCartPrice.contains(firstProductSRPrice), "cart price doesn't match with search result page price");
			Assert.assertTrue(firstProductCartPrice.contains(firstProductPDPrice),"cart price doesn't match with product page price");
			Assert.assertTrue(secondProductCartPrice.contains(secondProductSRPrice),"cart price doesn't match with search result page price");
			Assert.assertTrue(secondProductCartPrice.contains(secondProductPDPrice),"cart price doesn't match with product page price");
			
			System.out.println("Test Passed:Prices Matched");
		}finally {
			driver.quit();
		}
	}
}
