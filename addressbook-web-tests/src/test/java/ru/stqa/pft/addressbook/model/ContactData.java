package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String firstMail;
  private String group;

  public ContactData(String name, String lastname, String address, String homePhone, String firstMail, String group) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.firstMail = firstMail;
    this.group = group;
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
}
