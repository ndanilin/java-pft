package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Иван").withLastName("Петров")
                    .withPhoneHome("45-46-47").withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108")
                    .withMailFirst("ivan@mail.com"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifyContact.getId()).withFirstName("Пётр")
                .withLastName("Иванов").withPhoneHome("45-46-47")
                .withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108").withMailFirst("ivan@mail.com");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
        verifyContactListInUI();
    }
}
