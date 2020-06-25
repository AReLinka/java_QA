package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPassTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    Users users = app.db().usersWithoutAdmin();
    UserData user = users.iterator().next();
    String username = user.getName();
    String email = user.getEmail();
    String newPassword = "NewPass";

    app.session().loginAsAdmin();
    app.goTo().managePage();
    app.goTo().manageUsersTab();
    app.user().selectById(user.getId());
    app.user().initPasswordReset();

    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = app.mail().findConfirmationLink(email, mailMessages);
    app.registration().finish(confirmationLink, newPassword);
    assertTrue (app.newSession().login(username, newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}