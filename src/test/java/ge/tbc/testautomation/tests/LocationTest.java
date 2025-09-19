package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.LocationConstants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.DefaultMapSteps;
import ge.tbc.testautomation.steps.LocationFilterSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class LocationTest extends BaseTest {
    BrowserContext browserContext;
    DefaultMapSteps defaultMapSteps;
    LocationFilterSteps locationFilterSteps;

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
        locationFilterSteps = new LocationFilterSteps(page);
    }

    @Test
    public void defaultMapStateTest() {
        defaultMapSteps.checkAllTabIsActive(LocationConstants.DEFAULT_BUTTON_TEXT);
        Assert.assertTrue(defaultMapSteps.geoLocationIsInRange(LocationConstants.TBILISI_MIN_LAT, LocationConstants.TBILISI_MAX_LAT, LocationConstants.TBILISI_MIN_LNG, LocationConstants.TBILISI_MAX_LNG));
        Assert.assertFalse(defaultMapSteps.geoLocationIsInRange(1, 2, 3, 4));
    }

    @Test
    public void searchByInvalidKeywordTest() {
        locationFilterSteps
                .fillLocationInput(LocationConstants.INVALID_LOCATION)
                .invalidSearchTitleIsVisible()
                .invalidSearchDescriptionIsVisible();
    }

    @Test
    public void searchLocationTest() {
        locationFilterSteps.loadLocationPins();
        int initialCount = locationFilterSteps.getLocationPinCount();
        locationFilterSteps
                .fillLocationInput(LocationConstants.LOCATION)
                .loadLocationPins();
        int updatedCount = locationFilterSteps.getLocationPinCount();
        Assert.assertTrue(updatedCount < initialCount);

        locationFilterSteps
                .loadLocationTitles()
                .locationTitlesAreVisible()
                .loadCurrencies()
                .currenciesAreVisible()
                .loadBankTypeLabels()
                .bankTypeLabelsAreVisible()
                .loadBankTimesOpen()
                .bankTimesOpenAreVisible();
    }
}
