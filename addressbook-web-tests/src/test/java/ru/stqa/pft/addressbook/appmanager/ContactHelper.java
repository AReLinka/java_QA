package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getFirstMail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
    closeAlert();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    //верхняя кнопка
    click(By.name("update"));
  }

  public void submitContactDeletion() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void waitForDelMessage() {
    boolean check;
    isElementPresent(By.cssSelector("div.msgbox"));
    Assert.assertTrue(isElementPresent(By.cssSelector("div.msgbox")));

    //check = isMessagePresent(By.cssSelector("div.msgbox"), "1Record successful deleted");
    //Assert.assertTrue(check);
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    waitForDelMessage();
  }

  public void deleteInModification(ContactData contact) {
    initContactModificationById(contact.getId());
    submitContactDeletion();
    contactCache = null;
    waitForDelMessage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      // 0 элемент - ячейка, соджержащая чек-бокс, не нужна
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = cells.get(1).getText();
      String name = cells.get(2).getText();
      String address = cells.get(3).getText();
      String mail = cells.get(4).getText();
      String phone = cells.get(5).getText();
      //System.out.println(id + " " + name + " " + lastName + " " + address + " " + mail + " " + phone);
      ContactData contact = new ContactData()
              .withId(id).withName(name).withLastname(lastName)
              .withAddress(address).withHomePhone(phone).withFirstMail(mail);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

}
