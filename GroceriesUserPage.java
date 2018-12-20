package com.ibm.groceriespages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GroceriesUserPage {

	@FindBy(xpath = "//input[@placeholder='Search for products...']")
	WebElement searchEle;

	@FindBy(xpath = "(//div[@class='input-group']/descendant::input[1])[2]")
	WebElement searchEle2;

	@FindBy(xpath = "//div[@id='searchproducts-div']/descendant::a[1]")
	WebElement productNewLink;

	// To locate email link on user page
	@FindBy(xpath = "(//div[@class='header-mid-right-content']/descendant::a[1])[2]")
	WebElement emailLink;

	// To locate phone link on user page
	@FindBy(xpath = "(//div[@class='header-mid-right-content']/descendant::a[1])[1]")
	WebElement phoneLink;

	WebDriverWait wait;
	WebDriver driver;

	public GroceriesUserPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String searchProduct(String proudctName) throws InterruptedException {

		searchEle.sendKeys(proudctName);
		searchEle.click();

		// To enter product name in search text box of popup
		searchEle2.sendKeys(proudctName);
		Thread.sleep(10000);
		productNewLink.click();

		return driver.getPageSource();

	}

	// To verify the new address on user page
	public String verifyAddress() {
		return driver.getPageSource();
	}

	// To verify the new email link is on user page
	public boolean verifyEmail(String newemail) {

		if (emailLink.getText().contains(newemail))

			return true;
		else
			return false;
	}

	// To verify the new phone number is user page
	public boolean verifyPhone(String newphone) {

		if (phoneLink.getText().contains(newphone))

			return true;
		else
			return false;

	}

}
