package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("MyFirstGroup"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
                      .withName("Alina").withLastname("Sandyga").withAddress("Saint-Petersburg")
                      .withHomePhone("89111232233").withFirstMail("1@1.ru")
                      .inGroup(groups.iterator().next())
              , true);
    }
  }

  @Test
  //Второй адрес не подтягивается на главную страницу, тут простое сравнение
  public void testAddress() {
    app.goTo().HomePage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
