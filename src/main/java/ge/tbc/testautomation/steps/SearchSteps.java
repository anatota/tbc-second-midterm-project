package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.SearchPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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


    public boolean validateSearchResults(String searchQuery) {
        searchPage.loadSearchResults();
        return searchPage.searchResults
                .stream()
                .anyMatch(result -> result.textContent().contains(searchQuery));
    }

    public SearchSteps resultNotFoundValidation() {
        assertThat(searchPage.notFoundResult).isVisible();
        return this;
    }
}
