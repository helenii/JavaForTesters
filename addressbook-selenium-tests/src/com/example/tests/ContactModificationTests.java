package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();

		//save state
		List<ContactData> oldList = app.getContactHelper().getContacts();

		//action
		app.getContactHelper().modifyContact(0);
		ContactData contact = new ContactData();
		contact.lastname = "new lastname";
		contact.firstname = "new firstname";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().openMainPage();

		//save new state
		List<ContactData> newList = app.getContactHelper().getContacts();

		//compare states
		oldList.remove(0);
		oldList.add(contact);
		Collections.sort(oldList);
		assertEquals(newList, oldList);
	}

}
