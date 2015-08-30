package com.example.fw;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends WebDriverHelperBase{

	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public ContactHelper createContact(ContactData contact) {
		manager.navigateTo().mainPage();
		addNewContact();
		fillContactForm(contact, CREATION);
		submitContactCreation();
		manager.navigateTo().mainPage();
		// update model
		manager.getModel().addContact(contact);
		return this;
	}

	public ContactHelper modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		modifyContact(index);
		// get contact form
		compareContactFormWithDB();
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		manager.navigateTo().mainPage();
		manager.getModel().removeContact(index).addContact(contact);
		return this;
	}

	public ContactHelper deleteContact(int index) {
		manager.navigateTo().mainPage();
		initContactModification(index);
		submitContactDeletion();
		manager.navigateTo().mainPage();
		manager.getModel().removeContact(index);
		return this;
	}

	//-------------------------------------------------------------------------------------------

	public SortedListOf<ContactData> getUiContacts() {
		SortedListOf<ContactData> contacts = new SortedListOf<ContactData>();

		manager.navigateTo().mainPage();
		List<WebElement> rows = getContactRows();
		for (WebElement row : rows) {
			String lastname = row.findElement(By.xpath(".//td[2]")).getText();
			String firstname = row.findElement(By.xpath(".//td[3]")).getText();
			contacts.add(new ContactData().withLastname(lastname).withFirstname(firstname));
		}
		return contacts;
	}
	
	private void compareContactFormWithDB() {
		ContactData contactForm = getContactForm();
		ContactData contactDB = manager.getHibernateHelper().getContactData(Integer.parseInt(contactForm.getId()));
		assertThat(contactForm, equalTo(contactDB));
	}

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

	public ContactData getContactForm() {
		ContactData contact = new ContactData();
		contact.setId(driver.findElement(By.name("id")).getAttribute("value"));
		contact.setFirstname(driver.findElement(By.name("firstname")).getAttribute("value"));
		contact.setLastname(driver.findElement(By.name("lastname")).getAttribute("value"));
		contact.setAddress(driver.findElement(By.name("address")).getText());
		contact.setHomephone(driver.findElement(By.name("home")).getAttribute("value"));
		contact.setMobilephone(driver.findElement(By.name("mobile")).getAttribute("value"));
		contact.setWorkphone(driver.findElement(By.name("work")).getAttribute("value"));
		contact.setEmail(driver.findElement(By.name("email")).getAttribute("value"));
		contact.setEmail2(driver.findElement(By.name("email2")).getAttribute("value"));
		contact.setBday(driver.findElement(By.name("bday")).getText());
		contact.setBmonth(driver.findElement(By.name("bmonth")).getText());
		contact.setByear(driver.findElement(By.name("byear")).getText());
		contact.setAddress2(driver.findElement(By.name("address2")).getText());
		contact.setPhone2(driver.findElement(By.name("phone2")).getAttribute("value"));
		return contact;
	}

	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
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
		return this;
	}

	private List<WebElement> getContactRows() {
		return driver.findElements(By.xpath("//tr[@class]"));
	}

	private void submitContactDeletion() {
		click(By.xpath("//input[@value='Delete']"));
	}

}
