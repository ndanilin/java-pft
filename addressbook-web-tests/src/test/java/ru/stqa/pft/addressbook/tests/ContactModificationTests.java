package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Иван", "Петров", "45-46-47",
                    "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com", "test"), true);
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Пётр", "Иванов", "45-46-47","г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com");
        int index = before.size() - 1;
        app.getContactHelper().modify(contact, index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}
