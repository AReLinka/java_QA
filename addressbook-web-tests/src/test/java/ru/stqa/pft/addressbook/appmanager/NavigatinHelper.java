package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigatinHelper extends HelperBase{

  public NavigatinHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {

    click(By.linkText("groups"));
  }
}
