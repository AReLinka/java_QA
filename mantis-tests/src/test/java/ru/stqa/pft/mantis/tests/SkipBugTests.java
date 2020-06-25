package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SkipBugTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() throws RemoteException, ServiceException, MalformedURLException {
    if (app.db().openIssues().size() == 0) {
      Set<Project> projects = app.soap().getProjects();
      Issue issue = new Issue().withSummary("Open issue").withDescription("Test Description")
              .withProject(projects.iterator().next());
      app.soap().addIssue(issue);
    }
  }

  @Test
  public void testSkipIfOpen() throws RemoteException, ServiceException, MalformedURLException {
    int issueId = app.db().openIssues().stream().mapToInt(
            (i) -> i.getId()).findAny().getAsInt();
    skipIfNotFixed(issueId);
  }

  @Test
  public void testRunIfFix() throws RemoteException, ServiceException, MalformedURLException {
    int issueId = app.db().fixIssues().stream().mapToInt(
            (i) -> i.getId()).findAny().getAsInt();
   skipIfNotFixed(issueId);
  }
}
