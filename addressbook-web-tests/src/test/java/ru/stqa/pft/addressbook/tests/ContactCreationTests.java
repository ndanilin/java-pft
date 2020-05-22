package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
    private WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        login("admin", "secret");

    }

    @Test
    public void testGroupCreation() {
        initContactCreation();
        fillContactForm(new ContactData("Иван", "Петров", "45-46-47",
                "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com"));
        submitContactCreation();
        logout();
    }

    private void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.cssSelector("[value=Login]")).click();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void submitContactCreation() {
        wd.findElement(By.cssSelector("[name=submit]")).click();
    }

    private void fillContactForm(ContactData contactData) {
        wd.findElement(By.cssSelector("[name=firstname]")).sendKeys(contactData.getFirstName());
        wd.findElement(By.cssSelector("[name=lastname]")).sendKeys(contactData.getLastName());
        wd.findElement(By.cssSelector("[name=address]")).sendKeys(contactData.getAddress());
        wd.findElement(By.cssSelector("[name=home]")).sendKeys(contactData.getPhoneHome());
        wd.findElement(By.cssSelector("[name=email]")).sendKeys(contactData.getMail());
    }

    private void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

}
