package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class LocationPage {
    public Locator defaultTab;
    public List<Locator> markers;

    public LocationPage(Page page) {
        this.defaultTab = page.locator("//button[contains(@class, 'tbcx-pw-tab-menu__item active')]");
    }

    public void loadMarkers() {
        defaultTab.page().waitForSelector("//gmp-advanced-marker");
        this.markers = defaultTab.page().locator("//gmp-advanced-marker").all();
    }
}
