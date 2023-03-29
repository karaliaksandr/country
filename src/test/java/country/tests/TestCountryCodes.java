package country.tests;

import country.constants.TestData;
import country.controllers.CountryController;
import country.model.CountryModel;
import country.model.ErrorMessageModel;
import country.service.Service;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCountryCodes {
    private final Service service = new Service();

    @Test(description = "Verify that border countries match the prepared country", dataProvider = "preparedCountry")
    public void verifyCountryBorders(CountryModel countryModel){
        for (String borderCountryCode : countryModel.getBorders()) {
            Assert.assertTrue(service.getCountryCodesList(borderCountryCode).stream()
                    .anyMatch(countryCode -> countryCode.equals(countryModel.getCode())), borderCountryCode + " has no borders with " + countryModel.getCode());
        }
    }

    @Test(description = "Verify country borders", dataProvider = "preparedCountry")
    public void verifyCountryInfo(CountryModel countryModel){
        Response response = new CountryController()
                .getCountryInfo(countryModel.getCode());
        CountryModel[] countryModels = response.as(CountryModel[].class);
        Assert.assertEquals(countryModel, service.getModelByName(countryModels, countryModel.getName()));
    }

    @Test(description = "Perform GET request with non-existent country code")
    public void verifyNonExistentCountryCode(){
        CountryModel countryModel = TestData.NON_EXISTENT;
        ErrorMessageModel expectedError = TestData.NOT_FOUND_ERROR;
        Response response = new CountryController()
                .getCountryInfo(countryModel.getCode());
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        Assert.assertEquals(actualError, expectedError);
    }

    @DataProvider
    public Object[][] preparedCountry(){
        return new Object[][]{
                {TestData.RUS},
                {TestData.USA}
        };
    }
}
