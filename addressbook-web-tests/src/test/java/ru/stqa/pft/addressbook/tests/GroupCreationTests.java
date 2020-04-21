package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  // Создание новой группы
  public void testGroupCreation() {
    app.getNavigatinHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("MyFirstGroup", null, null));
  }

}
