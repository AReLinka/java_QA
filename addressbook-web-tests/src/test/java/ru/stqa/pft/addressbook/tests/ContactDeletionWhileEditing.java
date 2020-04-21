package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionWhileEditing extends TestBase {
  @Test
  public void testContactDeletionWhileEditing() {
    app.getNavigatinHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("MyFirstGroup", null, null));
    }

    app.getNavigatinHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Alina", "Sandyga", "Saint-Petersburg", "8(911)123-22-33", "1@1.ru", "MyFirstGroup"), true);
    }
    app.getNavigatinHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().waitForDelMessage();
    app.getNavigatinHelper().gotoHomePage();;
    }
}