package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.SearchConstants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.SearchSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    BaseSteps baseSteps;
    SearchSteps searchSteps;
    private boolean isFirstRun = true;

    @BeforeMethod
    public void setUpBeforeMethod() {
        baseSteps = new BaseSteps(page);
        searchSteps = new SearchSteps(page);
        page.navigate(Constants.MAIN_PAGE);
        if(isFirstRun) {
            baseSteps.rejectCookies();
            isFirstRun = false;
        }
    }

    @Test
    public void searchSiteTest() {
        searchSteps
                .openSearch()
                .fillInput(SearchConstants.SEARCH_QUERY);
        Assert.assertTrue(searchSteps.validateSearchResults(SearchConstants.SEARCH_QUERY));
    }

    @Test
    public void searchByInvalidKeywordTest() {
        searchSteps
                .openSearch()
                .fillInput(SearchConstants.INVALID_SEARCH_QUERY)
                .resultNotFoundValidation();
    }
}
