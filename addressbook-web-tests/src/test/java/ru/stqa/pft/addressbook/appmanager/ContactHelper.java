package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactHelper extends HelperBase {

    private Contacts contactCash = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.cssSelector("[name=submit]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.cssSelector("[name=firstname]"), contactData.getFirstName());
        type(By.cssSelector("[name=lastname]"), contactData.getLastName());
        type(By.cssSelector("[name=address]"), contactData.getAddress());
        type(By.cssSelector("[name=home]"), contactData.getPhoneHome());
        type(By.cssSelector("[name=mobile]"), contactData.getPhoneMobile());
        type(By.cssSelector("[name=work]"), contactData.getPhoneWork());
        type(By.cssSelector("[name=email]"), contactData.getMailFirst());
        type(By.cssSelector("[name=email2]"), contactData.getMailSecond());
        type(By.cssSelector("[name=email3]"), contactData.getMailThird());
        attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                assertEquals(contactData.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.cssSelector("[value=Update]"));
    }

    public void selectContact(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void submitContactDeletion() {
        click(By.cssSelector("[value=Delete]"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContact(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        wd.findElement(By.name("add")).click();
    }

    public void returnToContactPage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCash = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCash = null;
        returnToContactPage();
    }

    public void delete(ContactData contactData) {
        selectContact(contactData.getId());
        submitContactDeletion();
        contactCash = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String phoneHome = wd.findElement(By.name("home")).getAttribute("value");
        String phoneMobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String phoneWork = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String mailFirst = wd.findElement(By.name("email")).getAttribute("value");
        String mailSecond = wd.findElement(By.name("email2")).getAttribute("value");
        String mailThirst = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withPhoneHome(phoneHome).withPhoneMobile(phoneMobile).withPhoneWork(phoneWork)
                .withAddress(address)
                .withMailFirst(mailFirst).withMailSecond(mailSecond).withMailThird(mailThirst);
    }

    public Contacts all() {
        if (contactCash != null) {
            return contactCash;
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement e : elements) {
            String lastName = e.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = e.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String allPhones = e.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String address = e.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String allMails = e.findElement(By.cssSelector("td:nth-child(5)")).getText();
            int id = Integer.parseInt(e.findElement(By.cssSelector("input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName)
                    .withPhonesAll(allPhones)
                    .withAddress(address)
                    .withMailsAll(allMails);
            contactCash.add(contact);
        }
        return contactCash;
    }
}
