package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String name;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String firstMail;
  private String group;

  public void setId(int id) {
    this.id = id;
  }

  public ContactData(int id, String name, String lastname, String address, String homePhone, String firstMail, String group) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.firstMail = firstMail;
    this.group = group;
  }

  public ContactData(String name, String lastname, String address, String homePhone, String firstMail, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.firstMail = firstMail;
    this.group = group;
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

  public String getFirstMail() {
    return firstMail;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", firstMail='" + firstMail + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(name, that.name) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(firstMail, that.firstMail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastname, address, homePhone, firstMail);
  }


}
