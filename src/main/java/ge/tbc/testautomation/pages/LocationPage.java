package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LocationPage {
    public Locator defaultTab;

    public LocationPage(Page page) {
        this.defaultTab = page.locator("//button[contains(@class, 'tbcx-pw-tab-menu__item active')]");
    }
}
