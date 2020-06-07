package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String phoneHome;
    private final String address;
    private final String mail;
    private final String group;

    public ContactData(String firstName, String lastName, String phoneHome, String address, String mail, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneHome = phoneHome;
        this.address = address;
        this.mail = mail;
        this.group = group;
    }

    public ContactData(String lastName, String firstName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneHome = null;
        this.address = null;
        this.mail = null;
        this.group = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public String getGroup() {
        return group;
    }
}
