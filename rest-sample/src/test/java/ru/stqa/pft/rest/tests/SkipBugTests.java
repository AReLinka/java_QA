package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;

public class SkipBugTests extends TestBase {

  @Test
  public void testSkipIfOpen() throws Exception {
    int issueId = 146;
    skipIfNotFixed(issueId);
  }

  @Test
  public void testRunIfFix() throws Exception {
    int issueId = 145;
    skipIfNotFixed(issueId);
  }
}
