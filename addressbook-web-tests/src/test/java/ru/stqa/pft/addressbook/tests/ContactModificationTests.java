package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("MyFirstGroup", null, null));
    }

    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Alina", "Sandyga", "Saint-Petersburg", "89111232233", "1@1.ru", "MyFirstGroup"), true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "AlinaU", "SandygaU", "NSaint-Petersburg", "89111232234", "2@2.ru", null);
    int index = before.size() - 1;

    app.contact().modify(contact, index);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}
