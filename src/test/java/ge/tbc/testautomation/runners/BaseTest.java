package ge.tbc.testautomation.runners;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;

    protected BaseSteps baseSteps;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false).setSlowMo(1000); // remove this later
        browser = playwright.chromium().launch(launchOptions);
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
