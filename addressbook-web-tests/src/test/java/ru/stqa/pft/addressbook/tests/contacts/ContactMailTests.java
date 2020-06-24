package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Иван").withLastName("Петров")
                    .withMailFirst("ivan@mail.com").withMailSecond("ivan2@mail.com").withMailThird("ivan3@mail.com")
                    , true);
        }
    }

    @Test
    public void testContactMails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getMailsAll(), equalTo(mergeMails(contactInfoFromEditForm)));
    }

    private String mergeMails(ContactData contact) {
        return asList(contact.getMailFirst(), contact.getMailSecond(), contact.getMailThird())
                .stream().filter(s -> !s.equals(""))
                .map(ContactMailTests::cleaned)
                .collect(joining("\n"));
    }

    private static String cleaned(String mail) {
        return mail.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
