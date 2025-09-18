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

    public boolean geoLocationIsInRange(double minLat, double maxLat, double minLng, double maxLng) {
        locationPage.loadMarkers();

        if (locationPage.markers.isEmpty()) return false;

        long inRange = locationPage.markers.stream()
                .map(marker -> marker.getAttribute("position"))
                .filter(pos -> {
                    if (pos == null || !pos.contains(",")) return false;
                    try {
                        String[] parts = pos.split(",");
                        double lat = Double.parseDouble(parts[0]);
                        double lng = Double.parseDouble(parts[1]);
                        return lat >= minLat && lat <= maxLat && lng >= minLng && lng <= maxLng;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .count();

        return inRange > 0;
    }
}
