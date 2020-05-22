package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        login("admin", "secret");

    }

    protected void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.cssSelector("[value=Login]")).click();
    }

    protected void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    protected void submitGroupCreation() {
        wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    protected void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }

    protected void deleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();

    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
}
