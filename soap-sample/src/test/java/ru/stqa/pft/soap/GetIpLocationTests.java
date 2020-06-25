package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GetIpLocationTests {

  @Test
  public void testMyIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.185.27.12");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String location = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.185.27.Alina");
    assertNotEquals(location, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
  }
}

