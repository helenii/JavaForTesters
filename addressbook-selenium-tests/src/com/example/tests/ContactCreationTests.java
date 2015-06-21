package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


	@Test
	public void testContactCreation() throws Exception {
		openMainPage();
		addNewContact();
		ContactData contact = new ContactData();	
		contact.firstname = "John";
		contact.lastname = "Doe";
		contact.address = "Lenina street 10";
		contact.homephone = "1111111111";
		contact.mobilephone = "+79110004141";
		contact.workphone = "88888888";
		contact.email = "test@ya.ru";
		contact.email2 = "johndoe@yandex.ru";
		contact.bday = "1";
		contact.bmonth = "May";
		contact.byear = "1980";
		contact.group = "Rob";
		contact.address2 = "Nevskiy prospect 31";
		contact.phone2 = "4444444444";
		fillContactForm(contact);
		submitContactCreation();
	}

	@Test
	public void testEmptyContactCreation() throws Exception {
		openMainPage();
		addNewContact();
		fillContactForm(new ContactData("", "", "", "", "", "", "", "", "-", "-", "", "[none]", "", ""));
		submitContactCreation();
	}

	@Test
	public void testNoSecondaryAddressContactCreation() throws Exception {
		openMainPage();
		addNewContact();
		ContactData contact = new ContactData();
		contact.firstname = "Jenny";
		contact.lastname = "Doe";
		contact.address = "Hudson street 33";
		contact.homephone = "5555555555";
		contact.mobilephone = "+13244566779";
		contact.workphone = "000000000";
		contact.email = "test@gmail.com";
		contact.email2 = "test@gmail.com";
		contact.bday = "5";
		contact.bmonth = "December";
		contact.byear = "1986";
		contact.group = "Rob";
		contact.address2 = "";
		contact.phone2 = "";
		fillContactForm(contact);
		submitContactCreation();
	}
}
