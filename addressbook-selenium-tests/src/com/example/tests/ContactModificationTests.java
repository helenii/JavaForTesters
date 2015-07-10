package com.example.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactGenerator")
	public void modifySomeContact(ContactData contact) {
		app.getNavigationHelper().openMainPage();

		//save state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		Random rnd = new Random();
		int index = rnd.nextInt(oldList.size() - 1);
		
		//action
		app.getContactHelper().modifyContact(index);
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().openMainPage();

		//save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		//compare states
		oldList.remove(index);
		oldList.add(contact);
		Collections.sort(oldList);
		AssertJUnit.assertEquals(newList, oldList);
	}

}
