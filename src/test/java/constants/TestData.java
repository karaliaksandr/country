package constants;

import country.model.CountryModel;
import country.model.ErrorMessageModel;

import java.net.HttpURLConnection;
import java.util.Arrays;

public class TestData {
    public static final CountryModel RUS = new CountryModel("Russian Federation", "RUS", Arrays.asList("AZE", "BLR", "CHN", "EST", "FIN", "GEO", "KAZ", "PRK", "LVA", "LTU", "MNG", "NOR", "POL", "UKR"));
    public static final CountryModel USA = new CountryModel("United States of America", "USA", Arrays.asList("CAN", "MEX"));
    public static final CountryModel NON_EXISTENT = new CountryModel("Non existent country", "EEE", Arrays.asList(""));
    public static final ErrorMessageModel NOT_FOUND_ERROR = new ErrorMessageModel(HttpURLConnection.HTTP_NOT_FOUND, "Not Found");

    private static final String message = "message";
}
