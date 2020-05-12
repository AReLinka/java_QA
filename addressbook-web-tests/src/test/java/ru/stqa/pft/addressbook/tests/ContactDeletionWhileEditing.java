package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactDeletionWhileEditing extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }

    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Alina").withLastname("Sandyga").withAddress("Saint-Petersburg")
              .withHomePhone("89111232233").withFirstMail("1@1.ru").withGroup("MyFirstGroup")
              ,true);
    }
  }

  @Test
  public void testContactDeletionWhileEditing() {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().deleteInModification(deletedContact);
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
    }
}