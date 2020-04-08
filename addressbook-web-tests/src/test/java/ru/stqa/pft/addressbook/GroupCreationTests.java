package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  // Тест создания новой группы
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("MyFirstGroup", "FHeader", "FFooter"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
