package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final int id;
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
        this.id = Integer.MAX_VALUE;
    }

    public ContactData(String firstName, String lastName, String phoneHome, String address, String mail) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneHome = phoneHome;
        this.address = address;
        this.mail = mail;
        this.group = null;
    }

    public ContactData(int id, String firstName, String lastName) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (!Objects.equals(firstName, that.firstName)) return false;
        return Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
