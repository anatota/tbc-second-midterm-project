package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.GlobalNavigationSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GlobalNavigationTest extends BaseTest {
    private boolean isFirstRun = true;
    BaseSteps baseSteps;

    GlobalNavigationSteps globalNavigationSteps;
    @BeforeMethod
    public void setUpBeforeMethod() {
        baseSteps = new BaseSteps(page);
        globalNavigationSteps = new GlobalNavigationSteps(page);

        page.navigate(Constants.MAIN_PAGE);
        if(isFirstRun) {
            baseSteps.rejectCookies();
            isFirstRun = false;
        }
    }

    @Test
    public void megaMenuElementsTest() {
        globalNavigationSteps
                .loadMenuItems()
                .megaMenuIsVisibleAndMenuItemsAreActive();
    }

    @Test(dataProviderClass = DataSupplier.class, dataProvider = "menuTitlesAndCrumbs")
    public void breadcrumbsTest(String subItem, String breadcrumb) {
        globalNavigationSteps
                .loadMenuItems()
                .hoverOnItem(Constants.FOR_ME)
                .goToSection(subItem)
                .loadBreadcrumbItems()
                .validateBreadcrumb(breadcrumb);
    }
}
