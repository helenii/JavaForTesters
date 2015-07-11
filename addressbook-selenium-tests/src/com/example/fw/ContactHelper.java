package com.example.fw;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase{

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<ContactData> cachedContacts;
	
	public SortedListOf<ContactData> getContacts() {
		if (cachedContacts == null) {
			rebuildContactCache();
		}
		return cachedContacts;
	}

	private void rebuildContactCache() {
		cachedContacts = new SortedListOf<ContactData>();

		manager.navigateTo().mainPage();
		List<WebElement> rows = getContactRows();
		for (WebElement row : rows) {
			String lastname = row.findElement(By.xpath(".//td[2]")).getText();
			String firstname = row.findElement(By.xpath(".//td[3]")).getText();
			cachedContacts.add(new ContactData().withLastname(lastname).withFirstname(firstname));
		}		
	}

	public ContactHelper createContact(ContactData contact) {
		manager.navigateTo().mainPage();
		addNewContact();
		fillContactForm(contact, CREATION);
		submitContactCreation();
		manager.navigateTo().mainPage();
		rebuildContactCache();
		return this;
	}

	public ContactHelper modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		modifyContact(index);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		manager.navigateTo().mainPage();
		rebuildContactCache();
		return this;
	}

	public ContactHelper deleteContact(int index) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		submitContactDeletion();
		manager.navigateTo().mainPage();
		rebuildContactCache();
		return this;
	}
	
	//-------------------------------------------------------------------------------------------

	public ContactHelper addNewContact() {
		click(By.linkText("add new"));
		return this;
	}

	public ContactHelper fillContactForm(ContactData contact, boolean formType) {
		type(By.name("firstname"), contact.getFirstname());
		type(By.name("lastname"), contact.getLastname());
		type(By.name("address"), contact.getAddress());
		type(By.name("home"), contact.getHomephone());
		type(By.name("mobile"), contact.getMobilephone());
		type(By.name("work"), contact.getWorkphone());
		type(By.name("email"), contact.getEmail());
		type(By.name("email2"), contact.getEmail2());
		selectByText(By.name("bday"), contact.getBday());
		selectByText(By.name("bmonth"), contact.getBmonth());
		type(By.name("byear"), contact.getByear());
		if (formType == CREATION) {
			//selectByText(By.name("new_group"), contact.getGroup());
		} else {
			if (driver.findElements(By.name("new_group")).size() != 0) {
				throw new Error("Group selector exists in contact modification form");
			}
		}
		type(By.name("address2"), contact.getAddress2());
		type(By.name("phone2"), contact.getPhone2());
		return this;
	}

	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;
	}

	private void initContactModification(int index) {
		click(By.xpath("(//a[contains(@href,'id')]/img[@title='Edit'])[" + (index + 1) + "]"));
	}

	public ContactHelper modifyContact(int index) {
		initContactModification(index);
		return this;
	}

	public ContactHelper submitContactModification() {
		click(By.xpath("//input[@value='Update']"));
		cachedContacts = null;
		return this;
	}

	private List<WebElement> getContactRows() {
		return driver.findElements(By.xpath("//tr[@class]"));
	}

	private void submitContactDeletion() {
		click(By.xpath("//input[@value='Delete']"));
		cachedContacts = null;
	}

}
