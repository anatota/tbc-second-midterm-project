package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class SectionPage extends BasePage {
    public List<Locator> breadcrumbItems;
    public Locator heading;

    public SectionPage(Page page) {
        super(page);
        this.heading = page.locator("//h1");
    }

    public void getBreadcrumbItems() {
        heading.page().waitForSelector("ul.tbcx-pw-breadcrumbs__items li");
        this.breadcrumbItems = heading.page().locator("ul.tbcx-pw-breadcrumbs__items li").all();
    }
}
