package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.LocationConstants;
import ge.tbc.testautomation.steps.DefaultMapSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        context = browser.newContext();
        page = context.newPage();
        defaultMapSteps = new DefaultMapSteps(page);
        page.navigate(LocationConstants.LOCATION_URL);
    }

    @Test
    public void defaultMapStateTest() {
        defaultMapSteps
                .checkAllTabIsActive(LocationConstants.DEFAULT_BUTTON_TEXT);
    }
}
