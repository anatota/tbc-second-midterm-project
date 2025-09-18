package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    public Locator search;
    public Locator cookieReject;

    public BasePage(Page page) {
        this.search = page.locator("//tbcx-icon[contains(@style, 'search')]");
        this.cookieReject = page.locator("//div[@class='tbcx-pw-cookie-consent']//button[contains(@class, 'secondary')]");
    }
}
