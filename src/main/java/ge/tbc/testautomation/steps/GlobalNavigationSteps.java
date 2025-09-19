package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.BasePage;
import ge.tbc.testautomation.pages.SectionPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GlobalNavigationSteps {
    Page page;
    BasePage basePage;
    SectionPage sectionPage;

    public GlobalNavigationSteps(Page page) {
        this.page = page;
        basePage = new BasePage(page);
        sectionPage = new SectionPage(page);
    }

    public GlobalNavigationSteps loadMenuItems() {
        basePage.getMenuItems();
        return this;
    }

    public GlobalNavigationSteps megaMenuIsVisibleAndMenuItemsAreActive() {
        basePage.menuItems
                .stream()
                .forEach(item -> {
                    item.hover();
                    assertThat(basePage.megaMenuContainer).isVisible();
                    assertThat(item).containsClass("active");
                });
        return this;
    }

    public GlobalNavigationSteps hoverOnItem(String item) {
        page.getByText(item).first().hover();
        return this;
    }

    public GlobalNavigationSteps goToSection(String sectionName) {
        page.getByText(sectionName).first().click();
        return this;
    }

    public GlobalNavigationSteps loadBreadcrumbItems() {
        sectionPage.getBreadcrumbItems();
        return this;
    }

    public GlobalNavigationSteps validateBreadcrumb(String breadcrumbName) {
        assertThat(sectionPage.breadcrumbItems.get(sectionPage.breadcrumbItems.size() - 1)).containsText(breadcrumbName);
        return this;
    }
}
