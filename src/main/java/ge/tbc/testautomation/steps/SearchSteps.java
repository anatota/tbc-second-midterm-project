package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.SearchPage;

public class SearchSteps {
    Page page;
    SearchPage searchPage;

    public SearchSteps(Page page) {
        this.page = page;
        searchPage = new SearchPage(page);
    }

    public SearchSteps openSearch() {
        searchPage.search.click();
        return this;
    }

    public SearchSteps fillInput(String keyword) {
        searchPage.searchBar.fill(keyword);
        return this;
    }


}
