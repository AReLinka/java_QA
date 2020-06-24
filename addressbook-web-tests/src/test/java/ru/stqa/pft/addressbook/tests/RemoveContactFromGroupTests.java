package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {
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
  public void testAddContactToGroup() {
    Contacts beforeContacts = app.db().contacts();
    ContactData removeContact = beforeContacts.iterator().next();
    GroupData testGroup = findOrAddGroup(removeContact);

    //Небольшой костыль для получения правильного значения getGroups, после подбора или добавления в группу
    ContactData beforeContact = removeContact;
    removeContact = app.db().contacts().stream()
            .filter((c) -> c.getId() == beforeContact.getId()).findFirst().get();
    Groups beforeContactGroups = removeContact.getGroups();

    app.goTo().HomePage();
    app.contact().removeContactFromGroup(removeContact, testGroup);

    ContactData testedContact = removeContact;
    removeContact = app.db().contacts().stream()
            .filter((c) -> c.getId() == testedContact.getId()).findFirst().get();

    assertThat(removeContact.getGroups().size(), equalTo(beforeContactGroups.size() - 1));
    Groups afterContactGroups = removeContact.getGroups();
    assertThat(afterContactGroups, equalTo(beforeContactGroups.without(testGroup)));
  }

  private GroupData findOrAddGroup(ContactData removeContact) {
    Groups groups = app.db().groups();
    Groups contactGroups = removeContact.getGroups();
    GroupData result;

    if (contactGroups.size() > 0) {
      result = contactGroups.iterator().next();
    } else {
      GroupData groupToAdd = groups.iterator().next();
      app.goTo().HomePage();
      app.contact().addContactToGroup(removeContact, groupToAdd);
      result = groupToAdd;
    }
    return result;
  }

}
