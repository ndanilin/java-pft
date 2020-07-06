package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.name;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(name("username"), username);
        type(name("email"), email);
        click(cssSelector("[type=submit]"));
    }

    public void finish(String confirmationLink, String username, String password) {
        wd.get(confirmationLink);
        type(name("realname"), username);
        type(name("password"), password);
        type(name("password_confirm"), password);
        click(cssSelector("[type=submit]"));
    }

    public void loginWithAdmin() {
        type(By.name("username"), app.getProperty("web.adminLogin"));
        click(cssSelector("[type=submit]"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(cssSelector("[type=submit]"));
    }

    public List<String> selectUser() {
        app.getDriver().findElements(By.cssSelector("tbody [href]"))
                .stream().filter(u -> !u.getText().equals("administrator")).collect(Collectors.toList())
                .get(0).click();
        String user = app.getDriver().findElement(By.name("username")).getAttribute("value");
        String email = app.getDriver().findElement(By.name("email")).getAttribute("value");
        app.getDriver().findElement(By.cssSelector("[value='Сбросить пароль']")).click();
        return new ArrayList<>(asList(user, email));
    }
}
