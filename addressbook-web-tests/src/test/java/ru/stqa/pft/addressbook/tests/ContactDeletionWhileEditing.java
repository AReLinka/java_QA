package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionWhileEditing extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }

    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .withName("Alina").withLastname("Sandyga").withAddress("Saint-Petersburg")
              .withHomePhone("89111232233").withFirstMail("1@1.ru").withGroup("MyFirstGroup")
              ,true);
    }
  }

  @Test
  public void testContactDeletionWhileEditing() {
    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().deleteInModification(index);
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
    }
}