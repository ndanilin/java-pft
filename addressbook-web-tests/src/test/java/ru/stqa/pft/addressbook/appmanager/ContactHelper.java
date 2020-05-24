package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void submitContactCreation() {
        click(By.cssSelector("[name=submit]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.cssSelector("[name=firstname]"), contactData.getFirstName());
        type(By.cssSelector("[name=lastname]"), contactData.getLastName());
        type(By.cssSelector("[name=address]"), contactData.getAddress());
        type(By.cssSelector("[name=home]"), contactData.getPhoneHome());
        type(By.cssSelector("[name=email]"), contactData.getMail());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void initContactModification() {
        click(By.cssSelector("[title=Edit]"));
    }

    public void submitContactModification() {
        click(By.cssSelector("[value=Update]"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void submitContactDeletion() {
        click(By.cssSelector("[value=Delete]"));
        wd.switchTo().alert().accept();
    }
}
