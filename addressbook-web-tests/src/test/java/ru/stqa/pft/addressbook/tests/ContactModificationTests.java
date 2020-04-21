package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("AlinaU", "SandygaU", "NSaint-Petersburg", "8(911)123-22-34", "2@2.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigatinHelper().returnToHomePage();
  }
}
