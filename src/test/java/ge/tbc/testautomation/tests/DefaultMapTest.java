package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.LocationConstants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.DefaultMapSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class DefaultMapTest extends BaseTest {
    BrowserContext browserContext;
    DefaultMapSteps defaultMapSteps;

    @BeforeMethod
    public void setUpBeforeMethod() {
        if (page != null) {
            page.close();
        }
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setGeolocation(LocationConstants.TBILISI_LATITUDE, LocationConstants.TBILISI_LONGITUDE)
                .setPermissions(Arrays.asList("geolocation")));
        context = browserContext;
        page = context.newPage();

        page.navigate(LocationConstants.LOCATION_URL);

        baseSteps = new BaseSteps(page);
        baseSteps.rejectCookies();

        defaultMapSteps = new DefaultMapSteps(page);
    }

    @Test
    public void defaultMapStateTest() {
        defaultMapSteps.checkAllTabIsActive(LocationConstants.DEFAULT_BUTTON_TEXT);
        Assert.assertTrue(defaultMapSteps.geoLocationIsInRange(LocationConstants.TBILISI_MIN_LAT, LocationConstants.TBILISI_MAX_LAT, LocationConstants.TBILISI_MIN_LNG, LocationConstants.TBILISI_MAX_LNG));
        Assert.assertFalse(defaultMapSteps.geoLocationIsInRange(1, 2, 3, 4));
    }
}
