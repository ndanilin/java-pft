package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoTests {

    @Test
    public void testGeo() {
        String country = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("93.81.23.235");
        Assert.assertTrue(country.contains("<Country>RU</Country>"));
    }
}
