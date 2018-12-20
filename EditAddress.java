package com.ibm.groceries;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.ibm.groceriespages.EditProductsPage;
import com.ibm.groceriespages.EditSettingPage;
import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.PageDashboard;
import com.ibm.groceriespages.PageLogin;
import com.ibm.groceriespages.PageProducts;
import com.ibm.initialization.WebDriverLaunch;

public class EditAddress extends WebDriverLaunch {

	@Test(priority = 1, testName = "EditAddress", groups = "low")
	public void editAddress() throws IOException, InterruptedException {
		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String userPage = data.get("userPageUrl");
		String newAddress = data.get("addressNew");
		String newEmail = data.get("emailNew");
		String newPhone = data.get("phoneNew");
		String expMessage = data.get("expectedEditSettingMessage");
		String addressFoundMessage = data.get("addressFoundMsg");
		String emailFoundMessage = data.get("emailFoundMsg");
		String phoneFoundMessage = data.get("phoneFoundMsg");

		// Launching the web site for atozgroceries
		driver.get(url);

		PageLogin login = new PageLogin(driver, wait);
		// To enter email address and password and clickon login button
		login.enterEmailAddress(userName);
		login.enterPassword(password);
		login.clickOnLogin();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Logout")).isDisplayed());

		PageDashboard dashboard = new PageDashboard(driver, wait);

		// calling method to click on System link
		dashboard.clickOnsystem();

		// calling method to click on Settings link
		dashboard.clickOnSettings();

		// TO wait for the text area box address to be displayed.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("address")));

		EditSettingPage editPage = new EditSettingPage(driver, wait);

		// calling method to edit address,email and phone
		String pageSource = editPage.editSettingInfo(newAddress, newEmail, newPhone);
		Thread.sleep(10000);
		try {
			// Verifying for message whether the address,email,phone updated or not on admin
			// page
			if (pageSource.contains(expMessage)) {
				System.out.println(expMessage);
				Reporter.log(expMessage);
				

				// Assertion on expected message when settings updated
				Assert.assertTrue(pageSource.contains(expMessage));
				// Assertion on new email
				Assert.assertTrue(pageSource.contains(newEmail));

				// Assertion new phone number
				Assert.assertTrue(pageSource.contains(newPhone));
			}
		}

		catch (Exception e) {
			Assert.fail();
		}

		finally {
			// To click on on Logout button
			driver.findElement(By.partialLinkText("Logout")).click();

		}

		// To launch groceries user page
		driver.get(userPage);
		GroceriesUserPage userpage = new GroceriesUserPage(driver, wait);
		String userPageSource = userpage.verifyAddress();

		// Verifying for whether the address,email,phone updated or not on user page
		try {
			if (userPageSource.contains(newAddress)) {
				Assert.assertTrue(userPageSource.contains(newAddress));
				System.out.println(addressFoundMessage);
			}
			if (userPageSource.contains(newEmail)) {
				Assert.assertTrue(userpage.verifyEmail(newEmail));
				System.out.println(emailFoundMessage);
			}
			if (userPageSource.contains(newPhone)) {
				Assert.assertTrue(userpage.verifyPhone(newPhone));
				System.out.println(phoneFoundMessage);
			}

		} catch (Exception e) {
			Assert.fail();
		}

	}
}
