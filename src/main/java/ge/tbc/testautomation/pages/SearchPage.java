package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchPage extends BasePage {
    public Locator searchBar;

    public SearchPage(Page page) {
        super(page);
        this.searchBar = page.locator("//input[@type='text']");
    }
}
