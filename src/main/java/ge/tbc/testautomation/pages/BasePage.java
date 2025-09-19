package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class BasePage {
    public Locator search;
    public Locator cookieReject;
    public List<Locator> menuItems;
    public Locator megaMenuContainer;

    public BasePage(Page page) {
        this.search = page.locator("//tbcx-icon[contains(@style, 'search')]");
        this.cookieReject = page.locator("//div[@class='tbcx-pw-cookie-consent']//button[contains(@class, 'secondary')]");
        this.megaMenuContainer = page.locator("div.ng-trigger-megaMenuContainerAnimation");
    }

    public void getMenuItems() {
        search.page().waitForSelector(".tbcx-pw-navigation tbcx-pw-navigation-item");
        this.menuItems = search.page().locator(".tbcx-pw-navigation tbcx-pw-navigation-item").all();
    }
}
