package ge.tbc.testautomation.runners;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.BaseSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;

    protected BaseSteps baseSteps;

    @BeforeClass
    @Parameters({"browserType", "device"})
    public void setUp(String browserType, String device) {
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false).setSlowMo(1000); // remove this later
//        launchOptions.setHeadless(true);

        if(device.equals("mobile")) {
            browser = playwright.chromium().launch(launchOptions);
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(393, 852));
        } else {
            if(browserType.equals("firefox")) {
                browser = playwright.firefox().launch(launchOptions);
            } else if (browserType.equals("chromium")) {
                browser = playwright.chromium().launch(launchOptions);
            } else {
                browser = playwright.webkit().launch(launchOptions);
            }
            context = browser.newContext();
        }

        page = context.newPage();
    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
