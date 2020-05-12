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
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }

    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
                      .withName("Alina").withLastname("Sandyga").withAddress("Saint-Petersburg")
                      .withHomePhone("89111232233").withFirstMail("1@1.ru").withGroup("MyFirstGroup")
              , true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withName("AlinaU")
            .withLastname("SandygaU").withAddress("NSaint-Petersburg")
            .withHomePhone("89111232234").withFirstMail("2@2.ru");

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
