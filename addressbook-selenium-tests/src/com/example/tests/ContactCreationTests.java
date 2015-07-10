package com.example.tests;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.fw.ApplicationManager;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
	
	@Test(dataProvider = "randomValidContactGenerator")
	public void testContactCreationWithValidData(ContactData contact) throws Exception {
		app.getNavigationHelper().openMainPage();

		//save state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		//action
		app.getContactHelper().addNewContact();
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactCreation();
		app.getNavigationHelper().openMainPage();

		//save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		//compare states
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);
	}
}
