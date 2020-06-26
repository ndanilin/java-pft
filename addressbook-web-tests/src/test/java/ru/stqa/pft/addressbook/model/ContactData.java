package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName = "";

    @Expose
    @Column(name = "lastname")
    private String lastName = "";

    @Column(name = "home")
    @Type(type = "text")
    private String phoneHome = "";

    @Column(name = "mobile")
    @Type(type = "text")
    private String phoneMobile = "";

    @Column(name = "work")
    @Type(type = "text")
    private String phoneWork = "";

    @Column(name = "email")
    @Type(type = "text")
    private String mailFirst = "";

    @Column(name = "email2")
    @Type(type = "text")
    private String mailSecond = "";

    @Column(name = "email3")
    @Type(type = "text")
    private String mailThird = "";

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address = "";

    @Column(name = "photo")
    @Type(type = "text")
    private String photo = "";

    @Transient
    private String group;


    @Transient
    private String phonesAll;

    @Transient
    private String mailsAll;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhonesAll() {
        return phonesAll;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
    }

    public String getAddress() {
        return address;
    }

    public String getMailFirst() {
        return mailFirst;
    }

    public String getMailsAll() {
        return mailsAll;
    }

    public String getMailSecond() {
        return mailSecond;
    }

    public String getMailThird() {
        return mailThird;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }


    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withPhonesAll(String phonesAll) {
        this.phonesAll = phonesAll;
        return this;
    }

    public ContactData withPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
        return this;
    }

    public ContactData withPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
        return this;
    }

    public ContactData withPhoneWork(String phoneWork) {
        this.phoneWork = phoneWork;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMailsAll(String mailsAll) {
        this.mailsAll = mailsAll;
        return this;
    }

    public ContactData withMailFirst(String mail) {
        this.mailFirst = mail;
        return this;
    }

    public ContactData withMailSecond(String mailSecond) {
        this.mailSecond = mailSecond;
        return this;
    }

    public ContactData withMailThird(String mailThird) {
        this.mailThird = mailThird;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", phoneMobile='" + phoneMobile + '\'' +
                ", phoneWork='" + phoneWork + '\'' +
                ", mailFirst='" + mailFirst + '\'' +
                ", mailSecond='" + mailSecond + '\'' +
                ", mailThird='" + mailThird + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(phoneHome, that.phoneHome)) return false;
        if (!Objects.equals(phoneMobile, that.phoneMobile)) return false;
        if (!Objects.equals(phoneWork, that.phoneWork)) return false;
        if (!Objects.equals(mailFirst, that.mailFirst)) return false;
        if (!Objects.equals(mailSecond, that.mailSecond)) return false;
        if (!Objects.equals(mailThird, that.mailThird)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneHome != null ? phoneHome.hashCode() : 0);
        result = 31 * result + (phoneMobile != null ? phoneMobile.hashCode() : 0);
        result = 31 * result + (phoneWork != null ? phoneWork.hashCode() : 0);
        result = 31 * result + (mailFirst != null ? mailFirst.hashCode() : 0);
        result = 31 * result + (mailSecond != null ? mailSecond.hashCode() : 0);
        result = 31 * result + (mailThird != null ? mailThird.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
