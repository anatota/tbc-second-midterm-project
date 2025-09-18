package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.SearchSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SearchTest {
    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext context;

    SearchSteps searchSteps;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false).setSlowMo(1000); // remove this later
        browser = playwright.chromium().launch(launchOptions);
        context = browser.newContext();
        page = context.newPage();
        searchSteps = new SearchSteps(page);
        page.navigate(Constants.MAIN_PAGE);
    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
