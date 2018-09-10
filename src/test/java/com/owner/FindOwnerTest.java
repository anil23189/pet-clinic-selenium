package com.owner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vocalink.pages.FindOwner;
import com.vocalink.utility.PropertyFile;

public class FindOwnerTest {
	FindOwner page1 = new FindOwner();
	
	@Test(priority = 1)
	public void AddOwner() throws Exception
	{
		Thread.sleep(2000);
		boolean isownerAdded = page1.addowner(FindOwnerSuiteTest.driver, PropertyFile.read_testdata("firstname"),PropertyFile.read_testdata("lastname"));
		Thread.sleep(2000);			
		Assert.assertTrue(isownerAdded, "Data is not added");
	}

	@Test(priority =2)
	public void SearchOwner() throws Exception
	{
		Thread.sleep(2000);
		boolean IsUSerSearched = page1.SearchUser(FindOwnerSuiteTest.driver);
		Thread.sleep(2000);			
		Assert.assertTrue(IsUSerSearched, "Data is not found");
	}
}
