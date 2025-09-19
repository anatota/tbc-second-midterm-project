package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.DataSupplier;
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

    @Test(dataProviderClass = DataSupplier.class, dataProvider = "validSearchKeywords")
    public void searchSiteTest(String keyword) {
        searchSteps
                .openSearch()
                .fillInput(keyword);
        Assert.assertTrue(searchSteps.validateSearchResults(keyword));
    }

    @Test(dataProviderClass = DataSupplier.class, dataProvider = "invalidSearchKeywords")
    public void searchByInvalidKeywordTest(String invalidKeyword) {
        searchSteps
                .openSearch()
                .fillInput(invalidKeyword)
                .resultNotFoundValidation();
    }
}
