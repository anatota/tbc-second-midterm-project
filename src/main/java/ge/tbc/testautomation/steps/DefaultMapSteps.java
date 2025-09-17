package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LocationPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DefaultMapSteps {
    Page page;
    LocationPage locationPage;

    public DefaultMapSteps(Page page) {
        this.page = page;
        this.locationPage = new LocationPage(page);
    }

    public DefaultMapSteps checkAllTabIsActive(String text) {
        assertThat(locationPage.defaultTab).containsText(text);
        return this;
    }
}
