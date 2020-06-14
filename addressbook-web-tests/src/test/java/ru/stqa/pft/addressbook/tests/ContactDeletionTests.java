package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData()
                    .withFirstName("Иван").withLastName("Петров")
                    .withPhoneHome("45-46-47").withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108")
                    .withMail("ivan@mail.com").withGroup("test"), true);
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().getContactList();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
