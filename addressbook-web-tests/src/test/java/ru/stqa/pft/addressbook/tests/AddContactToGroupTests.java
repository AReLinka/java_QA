package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

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
    ContactData addedContact = beforeContacts.iterator().next();

    Groups beforeContactGroups = addedContact.getGroups();
    GroupData testGroup = findOrCreateGroup(addedContact);

    app.goTo().HomePage();
    app.contact().addContactToGroup(addedContact, testGroup);

    ContactData testedContact = addedContact;
    addedContact = app.db().contacts().stream()
            .filter((c) -> c.getId() == testedContact.getId()).findFirst().get();

    assertThat(addedContact.getGroups().size(), equalTo(beforeContactGroups.size() + 1));

    Groups afterContactGroups = addedContact.getGroups();

    assertThat(afterContactGroups, equalTo(beforeContactGroups.withAdded(testGroup)));

  }

  private GroupData findOrCreateGroup(ContactData addedContact) {
    Groups groups = app.db().groups();
    Groups contactGroups = addedContact.getGroups();

    GroupData result;

    if (contactGroups.size() < groups.size()) {
      for (GroupData group : contactGroups) {
        groups.remove(group);
      }
      result = groups.iterator().next();

    } else {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("GroupToAdd"));
      Groups newGroups = app.db().groups();
      int maxId = newGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt();
      result = newGroups.stream().filter((g) -> g.getId() == maxId).findFirst().get();
    }
    return result;
  }
}
