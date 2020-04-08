package ru.stqa.pft.addressbook;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String firstMail;

  public ContactData(String name, String lastname, String address, String homePhone, String firstMail) {
    this.name = name;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.firstMail = firstMail;
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
}
