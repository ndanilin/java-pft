package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Иван").withLastName("Петров")
                    .withPhoneHome("45-46-47").withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108")
                    .withMailFirst("ivan@mail.com"), true);
        }

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test"));
        }
    }


    @Test
    public void testContactToGroup() {
        ContactData contact;
        Contacts contacts;
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        contacts = new Contacts(app.db().contacts().
                stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()));
        if (contacts.isEmpty()){
            contact = new ContactData().withFirstName("Иван").withLastName("Петров");
            app.contact().create(contact, true);
            contacts = new Contacts(app.db().contacts().
                    stream().filter(e -> (e.getGroups().isEmpty())).collect(Collectors.toSet()));
        }
        contact = contacts.iterator().next();

        app.contact().addToGroup(contact, group);
        app.goTo().homePage();
        assertThat(contact.getGroups().contains(group), equalTo(true));
    }
}
