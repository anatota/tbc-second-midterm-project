package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductDetailsSteps {
    Page page;

    public ProductDetailsSteps(Page page) throws IOException {
        this.page = page;
    }

    public ProductDetailsSteps compareSnapshots() throws IOException {
        String expectedSnapshot = new String(Files.readAllBytes(Paths.get("expected_snapshot.txt")), StandardCharsets.UTF_8);
        assertThat(page.locator("body")).matchesAriaSnapshot(expectedSnapshot);
        return this;
    }

    public ProductDetailsSteps generateSnapshot() throws IOException {
        String snapshot = page.locator("body").ariaSnapshot();
        Files.write(Paths.get("expected_snapshot.txt"), snapshot.getBytes(StandardCharsets.UTF_8));
        return this;
    }
}
