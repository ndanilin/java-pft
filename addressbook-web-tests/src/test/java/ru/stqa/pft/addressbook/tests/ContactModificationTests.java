package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Иван", "Петров", "45-46-47",
                    "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com", "test"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Пётр", "Иванов", "45-46-47",
                "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToContactPage();
    }
}
