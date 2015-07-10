package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase{

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void addNewContact() {
		click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contact) {
		type(By.name("firstname"), contact.firstname);
		type(By.name("lastname"), contact.lastname);
		type(By.name("address"), contact.address);
		type(By.name("home"), contact.homephone);
		type(By.name("mobile"), contact.mobilephone);
		type(By.name("work"), contact.workphone);
		type(By.name("email"), contact.email);
		type(By.name("email2"), contact.email2);
		selectByText(By.name("bday"), contact.bday);
		selectByText(By.name("bmonth"), contact.bmonth);
		type(By.name("byear"), contact.byear);
		selectByText(By.name("new_group"), contact.group);
		type(By.name("address2"), contact.address2);
		type(By.name("phone2"), contact.phone2);
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	}

	private void initContactModification(int index) {
		click(By.xpath("(//a[contains(@href,'id')]/img[@title='Edit'])[" + (index + 1) + "]"));
	}

	public void modifyContact(int index) {
		initContactModification(index);
	}

	public void submitContactModification() {
		click(By.xpath("//input[@value='Update']"));
	}

	public void deleteContact(int index) {
		initContactModification(index);
		click(By.xpath("//input[@value='Delete']"));
	}

	public List<ContactData> getContacts() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> rows = getContactRows();
		for (WebElement row : rows) {
			ContactData contact = new ContactData();
			contact.lastname = row.findElement(By.xpath(".//td[2]")).getText();
			contact.firstname = row.findElement(By.xpath(".//td[3]")).getText();
			contacts.add(contact);
		}
		return contacts;
	}

	private List<WebElement> getContactRows() {
		return driver.findElements(By.xpath("//tr[@class]"));
	}
}
