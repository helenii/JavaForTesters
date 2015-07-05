package com.example.tests;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

	@Test
	public void testNonEmptyGroupCreation() throws Exception {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();

		//save state
		List<GroupData> oldList = app.getGroupHelper().getGroups();

		//action
		app.getGroupHelper().initGroupCreation();
		GroupData group = new GroupData();
		group.name = "group name 1";
		group.header = "header 1";
		group.footer = "footer 1";
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupCreation();
		app.getGroupHelper().returnToGroupsPage();

		//save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();

		//compare states
		oldList.add(group);
		Collections.sort(oldList);
		assertEquals(newList, oldList);
	}

	@Test
	public void testEmptyGroupCreation() throws Exception {
		app.getNavigationHelper().openMainPage();
		app.getNavigationHelper().gotoGroupsPage();
		
		//save state
		List<GroupData> oldList = app.getGroupHelper().getGroups();

		//action
		GroupData group = new GroupData("", "", "");
		app.getGroupHelper().initGroupCreation();
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupCreation();
		app.getGroupHelper().returnToGroupsPage();
		
		//save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();

		//compare states
		oldList.add(group);
		Collections.sort(oldList);
		assertEquals(newList, oldList);
	}
}
