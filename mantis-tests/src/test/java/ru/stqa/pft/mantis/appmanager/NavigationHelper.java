package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void managePage() {
    click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_overview_page.php']"));
  }

  public void manageUsersTab() {
    click(By.cssSelector("a[href='/mantisbt-2.24.1/manage_user_page.php']"));
  }
}