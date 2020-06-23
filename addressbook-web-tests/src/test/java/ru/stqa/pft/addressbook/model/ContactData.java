package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {
  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column (name = "firstname")
  private String name;
  @Expose
  @Column (name = "lastname")
  private String lastname;
  @Expose
  @Column (name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;
  @Transient
  private String allPhones;

  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String firstMail;
  @Column (name = "email2")
  @Type(type = "text")
  private String secondMail;
  @Column (name = "email3")
  @Type(type = "text")
  private String thirdMail;
  @Transient
  private String allMails;

  @Expose
  @Transient
  private String group;

  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withFirstMail(String firstMail) {
    this.firstMail = firstMail;
    return this;
  }

  public ContactData withSecondMail(String secondMail) {
    this.secondMail = secondMail;
    return this;
  }

  public ContactData withThirdMail(String thirdMail) {
    this.thirdMail = thirdMail;
    return this;
  }

  public ContactData withAllMails(String allMails) {
    this.allMails = allMails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }
  public String getAllPhones() {
    return allPhones;
  }

  public String getFirstMail() {
    return firstMail;
  }

  public String getSecondMail() {
    return secondMail;
  }

  public String getThirdMail() {
    return thirdMail;
  }

  public String getAllMails() {
    return allMails;
  }

  public String getGroup() {
    return group;
  }

  public String getPhoto() {
    return photo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(firstMail, that.firstMail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname, address, homePhone, firstMail);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", firstMail='" + firstMail + '\'' +
            ", secondMail='" + secondMail + '\'' +
            ", thirdMail='" + thirdMail + '\'' +
            '}';
  }

}
