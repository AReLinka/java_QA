package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  // Создание нового контакта
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Alina", "Sandyga", "Saint-Petersburg", "8(911)123-22-33", "1@1.ru"));
    submitContactCreation();
    returnToHomePage();
  }

}
