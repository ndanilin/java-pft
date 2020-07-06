package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException {
        String password = "password_new";

        app.registration().loginWithAdmin();
        app.getDriver().get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        List<String> cred = app.registration().selectUser();
        List<MailMessage> mailMessages = app.mail().waitForMail(0, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, cred.get(1));
        app.registration().finish(confirmationLink, cred.get(0), password);
        assertTrue(app.newSession().login(cred.get(0), password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
