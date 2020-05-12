package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

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
              , true);
    }
  }

  @Test
  public void testContactModification() {

    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData()
            .withId(modifyContact.getId()).withName("AlinaU")
            .withLastname("SandygaU").withAddress("NSaint-Petersburg")
            .withHomePhone("89111232234").withFirstMail("2@2.ru");

    app.contact().modify(contact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contact);

    Assert.assertEquals(before, after);
  }
}
