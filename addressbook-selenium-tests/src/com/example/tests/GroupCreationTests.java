package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> groupsFromFile() throws IOException {
		return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}

	@Test(dataProvider = "groupsFromFile")
	public void testGroupCreationWithValidData(GroupData group) throws Exception {
		//save state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getUiGroups();

		//action
		app.getGroupHelper().createGroup(group);

		//save new state
		SortedListOf<GroupData> newList = app.getModel().getGroups();

		//compare states
		assertThat(newList, equalTo(oldList.withAdded(group)));
		
		//compare model to implementation
		if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {

				assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUiGroups()));
			}
		}
	}
}
