package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("MyFirstGroup", null, null));
    }

    app.goTo().HomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Alina", "Sandyga", "Saint-Petersburg", "8(911)123-22-33", "1@1.ru", "MyFirstGroup"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().waitForDelMessage();
    //тут наверное можно поставить ожидание автоматического возврата
    app.goTo().HomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove((before.size() - 1));
        Assert.assertEquals(before, after);

  }
}
