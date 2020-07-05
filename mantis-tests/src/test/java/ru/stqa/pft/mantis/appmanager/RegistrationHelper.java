package ru.stqa.pft.mantis.appmanager;

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

    public void finish(String confirmationLink,String username, String password) {
        wd.get(confirmationLink);
        type(name("realname"), username);
        type(name("password"), password);
        type(name("password_confirm"), password);
        click(cssSelector("[type=submit]"));
    }
}
