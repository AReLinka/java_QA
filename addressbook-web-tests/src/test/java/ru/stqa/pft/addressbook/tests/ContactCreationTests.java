package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  // Создание нового контакта
  public void testContactCreation() {
    app.initContactCreation();
    app.fillContactForm(new ContactData("Alina", "Sandyga", "Saint-Petersburg", "8(911)123-22-33", "1@1.ru"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
