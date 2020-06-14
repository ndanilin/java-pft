package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Иван").withLastName("Петров")
                    .withPhoneHome("+7 (910)").withPhoneMobile("22-33-44").withPhoneWork("333")
                    .withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108")
                    .withMail("ivan@mail.com")
                    .withGroup("test"), true);
        }
    }

    @Test
    public void testContactAddresses() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    }

    private static String cleaned(String address) {
        return address.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
