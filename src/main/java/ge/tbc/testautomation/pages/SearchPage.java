package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class SearchPage extends BasePage {
    public Locator searchBar;
    public List<Locator> searchResults;
    public Locator notFoundResult;

    public SearchPage(Page page) {
        super(page);
        this.searchBar = page.locator("//input[@type='text']");
        this.notFoundResult = page.locator("//p[contains(@class, 'global-search__bottom-content__container__not-fount-result__description')]");
    }

    public void loadSearchResults() {
        searchBar.page().waitForSelector("//h3[@class='search-result-item__title']");
        this.searchResults = searchBar.page().locator("//h3[@class='search-result-item__title']").all();
    }
}
