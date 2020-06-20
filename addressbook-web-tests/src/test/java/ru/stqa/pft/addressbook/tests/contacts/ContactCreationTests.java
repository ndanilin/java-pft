package ru.stqa.pft.addressbook.tests.contacts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {"test 1", "header 1", "footer 1"});
        list.add(new Object[] {"test 2", "header 2", "footer 2"});
        list.add(new Object[] {"test 3", "header 3", "footer 3"});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation() {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/avatar.jpg");
        ContactData contact = new ContactData()
                .withFirstName("Иван").withLastName("Петров").withPhoneHome("45-46-47")
                .withAddress("г. Москва, Ленинградский пр-т, д.5, кв.108").withMailFirst("ivan@mail.com")
                .withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test'"); // нельзя создавать контакт с апострофом
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
