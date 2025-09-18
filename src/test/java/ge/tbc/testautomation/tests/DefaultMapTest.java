package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.LocationConstants;
import ge.tbc.testautomation.steps.DefaultMapSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class DefaultMapTest {
    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext context;

    DefaultMapSteps defaultMapSteps;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false).setSlowMo(1000); // remove this later
        browser = playwright.chromium().launch(launchOptions);
        context = browser.newContext(new Browser.NewContextOptions()
                .setGeolocation(LocationConstants.TBILISI_LATITUDE, LocationConstants.TBILISI_LONGITUDE)
                .setPermissions(Arrays.asList("geolocation")));
        page = context.newPage();
        defaultMapSteps = new DefaultMapSteps(page);
        page.navigate(LocationConstants.LOCATION_URL);
    }

    @Test
    public void defaultMapStateTest() {
        defaultMapSteps.checkAllTabIsActive(LocationConstants.DEFAULT_BUTTON_TEXT);
        Assert.assertTrue(defaultMapSteps.latitudesAreInRange(LocationConstants.TBILISI_MIN_LAT, LocationConstants.TBILISI_MAX_LAT, LocationConstants.TBILISI_MIN_LNG, LocationConstants.TBILISI_MAX_LNG));
        Assert.assertFalse(defaultMapSteps.latitudesAreInRange(1, 2, 3, 4));
    }
}
