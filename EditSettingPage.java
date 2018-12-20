package com.ibm.groceriespages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditSettingPage {
	
	//To locate Address text area box
	@FindBy(xpath="//textarea[@id='address']")
	WebElement addressEle;
	
	//To locate email text box
	@FindBy(xpath="//input[@id='email']")
	WebElement emailEle;
	
	//To locate phonenumber text box
	@FindBy(xpath="//input[@id='phonenumber']")
	WebElement phoneEle;
		
	// To locate save button
	@FindBy(xpath = "//button[@title='Save']")
	WebElement saveEle;
		
	WebDriverWait wait;
	WebDriver driver;

	public EditSettingPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}
	
	
	public String editSettingInfo(String address,String email, String phone)
	{
		//To edit the address
		addressEle.clear();
		addressEle.sendKeys(address);
		
		
		//To edit email
		emailEle.clear();
		emailEle.sendKeys(email);
		
		//To edit phone number
		phoneEle.clear();
		phoneEle.sendKeys(phone);
		
		//To click on Save button
		saveEle.click();
		return driver.getPageSource();
	}



}
