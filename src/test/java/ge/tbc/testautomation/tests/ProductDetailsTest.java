package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.BaseSteps;
import ge.tbc.testautomation.steps.ProductDetailsSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductDetailsTest extends BaseTest {
    private boolean isFirstRun = true;
    ProductDetailsSteps productDetailsSteps;

    @BeforeMethod
    public void setUpBeforeMethod() throws IOException {
        baseSteps = new BaseSteps(page);
        productDetailsSteps = new ProductDetailsSteps(page);
        page.navigate(Constants.MONEY_TRANSFERS_PAGE);
        if(isFirstRun) {
            baseSteps.rejectCookies();
            isFirstRun = false;
        }
    }

    @Test
    public void moneyTransfersVisualTest() throws IOException {
        productDetailsSteps
                .generateSnapshot()
                .compareSnapshots();
    }
}
