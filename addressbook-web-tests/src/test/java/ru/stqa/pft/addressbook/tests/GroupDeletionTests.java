package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
    }
}
