package ru.stqa.pft.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String phoneHome;
    private final String address;
    private final String mail;

    public ContactData(String firstName, String lastName, String phoneHome, String address, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneHome = phoneHome;
        this.address = address;
        this.mail = mail;
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
}
