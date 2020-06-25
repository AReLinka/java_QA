package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

public class TestBase {
  protected static final ApplicationManager app
          = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
   }

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic(app.getProperty("userKey"), app.getProperty("pass"));
  }

    public boolean isIssueOpen(int issueId) throws Exception {
    String json = RestAssured.get(app.getProperty("urlWithoutFormat") + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    String status = parsed.getAsJsonObject().getAsJsonArray("issues").get(0).getAsJsonObject()
            .get("state_name").getAsString();
    return status.equals("Open");
  }

  public void skipIfNotFixed(int issueId) throws Exception {
    if (isIssueOpen(issueId)) {
      System.out.println("Issue is open");
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


}
