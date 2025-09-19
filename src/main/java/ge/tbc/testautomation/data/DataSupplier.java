package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class DataSupplier {
    @DataProvider(name = "menuTitlesAndCrumbs")
    public Object[][] menuTitlesAndCrumbs() {
        return new Object[][] {
                {"სესხები", "სესხები"},
                {"ბარათები", "ბარათები"},
                {"ანაბრები", "ანაბრები"},
                {"ციფრული სერვისები", "ციფრული სერვისები"},
                {"სხვა პროდუქტები", "სხვა პროდუქტები"},
                {"ახალი თაობისთვის", "ახალი თაობისთვის"},
                {"ემიგრანტებისთვის", "ემიგრანტებისთვის"},
                {"არარეზიდენტებისთვის", "არარეზიდენტებისთვის"},
        };
    }
}
