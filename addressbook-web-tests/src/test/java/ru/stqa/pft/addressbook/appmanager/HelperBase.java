package ru.stqa.pft.addressbook.appmanager;

import com.sun.org.apache.xpath.internal.operations.NotEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected  void closeAlert () {
    wd.switchTo().alert().accept();
  }

  public boolean isMessagePresent(By by, String message) {
    try {
      wd.findElement(by).getText().compareTo(message);
      System.out.println("1expectedMessage" + wd.findElement(by).getText());
      System.out.println("1real message = " + message);
      return true;
    } catch (NoSuchElementException  e) {
      System.out.println("expectedMessage" + wd.findElement(by).getText());
      System.out.println("real message = " + message);
      return false;
    }
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      System.out.println("ElementSuccessFound = " + by);
      return true;
    } catch (NoSuchElementException e) {
      System.out.println("ElementNotFound = " + by);
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
