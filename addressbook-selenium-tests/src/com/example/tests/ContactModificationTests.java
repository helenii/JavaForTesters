package com.example.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	
	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().modifyContact(1);
		ContactData contact = new ContactData();
		contact.lastname = "new lastname";
		contact.firstname = "new firstname";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().openMainPage();
	}

}
