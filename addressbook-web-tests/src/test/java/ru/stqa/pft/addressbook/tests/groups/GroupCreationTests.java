package ru.stqa.pft.addressbook.tests.groups;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        StringBuilder xml = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            xml.append(line);
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.alias("groups", GroupData.class);
        xStream.omitField(GroupData.class, "id");
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(String.valueOf(xml));
        return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContacts")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Set<GroupData> after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'"); //нельзя создавать группу с апострофом
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Set<GroupData> after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
