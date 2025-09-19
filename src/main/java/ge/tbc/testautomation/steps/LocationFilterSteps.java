package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LocationPage;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocationFilterSteps {
    Page page;
    LocationPage locationPage;

    public LocationFilterSteps(Page page) {
        this.page = page;
        locationPage = new LocationPage(page);
    }

    public LocationFilterSteps fillLocationInput(String location) {
        locationPage.locationInput.fill(location);
        return this;
    }

    public LocationFilterSteps invalidSearchTitleIsVisible() {
        assertThat(locationPage.invalidSearchTitle).isVisible();
        return this;
    }

    public LocationFilterSteps invalidSearchDescriptionIsVisible() {
        assertThat(locationPage.invalidSearchDescription).isVisible();
        return this;
    }

    public int getLocationPinCount() {
        return locationPage.markers.size();
    }

    public LocationFilterSteps loadLocationPins() {
        locationPage.getMarkers();
        return this;
    }

    public LocationFilterSteps loadLocationTitles() {
        locationPage.getTitles();
        return this;
    }

    public LocationFilterSteps loadCurrencies() {
        locationPage.getCurrencies();
        return this;
    }

    public LocationFilterSteps loadBankTypeLabels() {
        locationPage.getBankTypeLabels();
        return this;
    }

    public LocationFilterSteps loadBankTimesOpen() {
        locationPage.getBankTimesOpen();
        return this;
    }

    public LocationFilterSteps locationTitlesAreVisible() {
        detailsAreVisible(locationPage.locationTitles);
        return this;
    }

    public LocationFilterSteps currenciesAreVisible() {
        detailsAreVisible(locationPage.currencies);
        return this;
    }

    public LocationFilterSteps bankTypeLabelsAreVisible() {
        detailsAreVisible(locationPage.bankTypeLabels);
        return this;
    }

    public LocationFilterSteps bankTimesOpenAreVisible() {
        detailsAreVisible(locationPage.bankTimesOpen);
        return this;
    }

    public LocationFilterSteps detailsAreVisible(List<Locator> locators) {
        locators
                .stream()
                .forEach(locator -> assertThat(locator).isVisible());
        return this;
    }
}
