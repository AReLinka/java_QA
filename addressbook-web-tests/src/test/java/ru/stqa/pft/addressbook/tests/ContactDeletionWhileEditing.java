package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionWhileEditing extends TestBase {
  @Test
  public void testContactDeletionWhileEditing() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().submitContactDeletion();
    app.getContactHelper().waitForDelMessage();
    }
}