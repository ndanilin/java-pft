package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Иван").withLastName("Петров"), true);
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }

        if (app.db().groups().stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).isEmpty()) {
            GroupData groupTemp = app.db().groups().iterator().next();
            ContactData contactTemp = app.db().contacts().iterator().next();
            app.contact().addToGroup(contactTemp, groupTemp);
        }
    }

    @Test
    public void testContactFromGroup() {
        app.goTo().homePage();
        GroupData group = app.db().groups().
                stream().filter(g -> !g.getContacts().isEmpty()).collect(toSet()).
                iterator().next();
        ContactData contact = app.db().contacts().
                stream().filter(c -> c.getGroups().contains(group)).collect(toSet()).
                iterator().next();
        app.contact().removeFromGroup(contact, group);

        // Hibernate кеширует результаты, поэтому обновляем данные по нашему контакту:
        int contactId = contact.getId();
        contact = app.db().contacts().
                stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet()).
                iterator().next();
        assertThat(contact.getGroups(), not(contains(group)));
    }
}
