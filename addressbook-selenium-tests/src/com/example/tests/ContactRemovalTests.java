package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Random;

import com.example.utils.SortedListOf;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase {

	@Test
	public void deleteSomeContact() {
		//save state
		SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();

		Random rnd = new Random();
		int index = rnd.nextInt(oldList.size() - 1);

		//action
		app.getContactHelper().deleteContact(index);

		//save new state
		SortedListOf<ContactData> newList = app.getContactHelper().getContacts();

		//compare states
		assertThat(newList, equalTo(oldList.without(index)));
	}

}
