package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class LocationPage {
    public Locator defaultTab;
    public List<Locator> markers;
    public Locator locationInput;
    public Locator invalidSearchTitle;
    public Locator invalidSearchDescription;
    public List<Locator> locationTitles;
    public List<Locator> currencies;
    public List<Locator> bankTypeLabels;
    public List<Locator> bankTimesOpen;

    public LocationPage(Page page) {
        this.defaultTab = page.locator("//button[contains(@class, 'tbcx-pw-tab-menu__item active')]");
        this.locationInput = page.locator("//input[contains(@placeholder, 'ლოკაცია')]");
        this.invalidSearchTitle = page.locator("//div[contains(@class, 'tbcx-pw-atm-branches-section__empty-state-title')]");
        this.invalidSearchDescription = page.locator("//div[contains(@class, 'tbcx-pw-atm-branches-section__empty-state-caption')]");
    }

    public void getMarkers() {
        defaultTab.page().waitForSelector("//gmp-advanced-marker");
        this.markers = defaultTab.page().locator("//gmp-advanced-marker").all();
    }

    public void getTitles() {
        locationInput.page().waitForSelector("//div[@class='tbcx-pw-atm-branches-section__list-item-title tbcx-pw-title']");
        this.locationTitles = locationInput.page().locator("//div[@class='tbcx-pw-atm-branches-section__list-item-title tbcx-pw-title']").all();
    }

    public void getCurrencies() {
        locationInput.page().waitForSelector("//div[@class='tbcx-pw-atm-branches-section__list-item-currencies']//span[contains(@class, 'ng-star-inserted')]");
        this.currencies = locationInput.page().locator("//div[@class='tbcx-pw-atm-branches-section__list-item-currencies']//span[contains(@class, 'ng-star-inserted')]").all();
    }

    public void getBankTypeLabels() {
        locationInput.page().waitForSelector("//div[@class='tbcx-pw-atm-branches-section__list-item-label']");
        this.bankTypeLabels = locationInput.page().locator("//div[@class='tbcx-pw-atm-branches-section__list-item-label']").all();
    }

    public void getBankTimesOpen() {
        locationInput.page().waitForSelector("//div[@class='tbcx-pw-atm-branches-section__list-item-description']");
        this.bankTimesOpen = locationInput.page().locator("//div[@class='tbcx-pw-atm-branches-section__list-item-description']").all();
    }
}
