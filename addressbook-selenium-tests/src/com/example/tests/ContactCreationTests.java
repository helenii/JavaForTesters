package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.utils.SortedListOf;

public class ContactCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> contactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
	}
	
	@Test(dataProvider = "contactsFromFile")
	public void testContactCreationWithValidData(ContactData contact) throws Exception {
		//save state
		SortedListOf<ContactData> oldList = app.getContactHelper().getUiContacts();

		//action
		app.getContactHelper().createContact(contact);

		//save new state
		SortedListOf<ContactData> newList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());

		//compare states
		assertThat(newList, equalTo(oldList.withAdded(contact)));
		
		//compare model to implementation
//		if (wantToCheck()) {
//			if ("yes".equals(app.getProperty("check.db"))) {
//
//				assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));
//			}
//			if ("yes".equals(app.getProperty("check.ui"))) {
//				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUiContacts()));
//			}
//		}
	}
}
