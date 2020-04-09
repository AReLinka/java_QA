package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  // Тест создания новой группы
  public void testGroupCreation() {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("MyFirstGroup", "FHeader", "FFooter"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
