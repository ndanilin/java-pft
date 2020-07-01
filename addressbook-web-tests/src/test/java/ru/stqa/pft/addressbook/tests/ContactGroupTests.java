package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Иван").withLastName("Петров"), true);
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test"));
        }
    }

    @Test
    public void testContactToGroup() {
        app.goTo().homePage();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();

        // отбираем контакты без групп. Если таких нет, то создаём новый
        Contacts contacts = new Contacts(app.db().contacts().stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()));
        if (contacts.isEmpty()) {
            app.contact().create(new ContactData().withFirstName("Иван").withLastName("Петров"), true);
            contacts = new Contacts(app.db().contacts().stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()));
        }
        ContactData contact = contacts.iterator().next();

        app.contact().addToGroup(contact, group);
        app.goTo().homePage();

        // Hibernate кеширует результаты, поэтому обновляем данные по нашему контакту:
        int contactId = contact.getId();
        contact = new Contacts(app.db().contacts().stream().filter(e -> (e.getId() == contactId)).collect(Collectors.toSet())).iterator().next();

        assertThat(contact.getGroups().contains(group), equalTo(true));
    }
}
