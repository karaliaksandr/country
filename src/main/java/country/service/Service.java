package country.service;

import country.controllers.CountryController;
import country.model.CountryModel;
import io.restassured.response.Response;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Service {
    public List<String> getCountryCodesList (String borderCountryCode){
        Response response = new CountryController()
                .getCountryInfo(borderCountryCode);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, response.getStatusCode());
        CountryModel[] borderCountryModels = response.as(CountryModel[].class);
        return borderCountryModels[0].getBorders();
    }
    public CountryModel getModelByName(CountryModel[] models, String name) {
        List<CountryModel> list = Arrays.asList(models);
        try {
            return list.stream().filter((p) -> p.getName().equals(name)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found model in CountryModels with the name = " + name);
        }
    }
}
