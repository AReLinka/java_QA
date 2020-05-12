package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }
  }

  @Test
  // Создание нового контакта
  public void testContactCreation() {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withName("Alina").withLastname("Sandyga").withAddress("Saint-Petersburg")
            .withHomePhone("89111232233").withFirstMail("1@1.ru").withGroup("MyFirstGroup");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
