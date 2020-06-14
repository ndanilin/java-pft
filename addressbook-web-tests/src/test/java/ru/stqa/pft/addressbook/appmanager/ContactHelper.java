package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

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
        type(By.cssSelector("[name=email]"), contactData.getMail());
        if (creation) {
            if (contactData.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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

    private Contacts contactCash = null;

    public Contacts all() {
        if (contactCash != null) {
            return contactCash;
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement e : elements) {
            String lastName = e.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstName = e.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(e.findElement(By.cssSelector("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contactCash.add(contact);
        }
        return contactCash;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String phoneHome = wd.findElement(By.name("home")).getAttribute("value");
        String phoneMobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String phoneWork = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withPhoneHome(phoneHome).withPhoneMobile(phoneMobile).withPhoneWork(phoneWork);
    }
}
