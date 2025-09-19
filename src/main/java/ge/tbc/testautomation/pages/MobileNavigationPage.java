package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class MobileNavigationPage extends BasePage {
    public Locator hamburgerMenu;
    public List<Locator> menuItems;

    public MobileNavigationPage(Page page) {
        super(page);
        this.hamburgerMenu = page.locator("//tbcx-icon[contains(@style, 'burger-menu')]");
        this.menuItems = page.locator("//div[contains(@class, 'tbcx-pw-navigation-item')]").all();
    }
}
