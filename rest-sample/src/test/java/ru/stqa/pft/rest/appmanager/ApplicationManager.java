package ru.stqa.pft.rest.appmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

  private final Properties properties;
  private IssueHelper issue;


  public ApplicationManager() {
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public IssueHelper issue() {
    if (issue == null) {
      issue = new IssueHelper(this);
    }
    return issue;
  }

}
