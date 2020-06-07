package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Иван", "Петров", "45-46-47",
                    "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com", "test"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Пётр", "Иванов", "45-46-47","г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        before.sort(Comparator.comparing(ContactData::getLastName));
        after.sort(Comparator.comparing(ContactData::getLastName));
        Assert.assertEquals(after, before);

    }
}
