package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("Иван", "Петров", "45-46-47",
                "г. Москва, Ленинградский пр-т, д.5, кв.108", "ivan@mail.com", "test"), true);
    }

}
