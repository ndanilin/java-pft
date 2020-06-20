package ru.stqa.pft.addressbook.generators;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class GroupDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<GroupData> groups = generateGroups(count);
        save(groups, file);
    }

    private static void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group: groups){
            writer.write(format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(format("test %s", i))
                    .withHeader(format("header %s", i))
                    .withFooter(format("footer %s", i)));
        }
        return groups;
    }

    @Test
    public void test() throws IOException {
        List<GroupData> groups = generateGroups(5);
        save(groups, new File("src/test/resources/groups.txt"));
    }
}
