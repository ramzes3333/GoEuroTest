package pl.impaq.test;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import pl.impaq.test.model.CityData;
import pl.impaq.test.businessLogic.CityDataCsvWriter;
import pl.impaq.test.utils.PropertiesLoader;
import pl.impaq.test.utils.RestClient;

/**
 * Created by andrew on 28.09.16.
 */
public class GoEuroTest {

    public static void main(String[] args) {
        GoEuroTest goEuroTest = new GoEuroTest();
        goEuroTest.perform(args);
    }

    public void perform(String[] args) {
        try {
            validateInputArguments(args);

            PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
            String endpoint = propertiesLoader.getPropertyValue("endpoint");

            RestClient client = RestClient.getInstance();
            String response = client.getResponse(endpoint + getCityNameFromArguments(args));

            Gson gson = new Gson();
            CityData[] cityData = gson.fromJson(response, CityData[].class);

            CityDataCsvWriter cityDataCsvWriter = CityDataCsvWriter.getInstance();
            cityDataCsvWriter.write(cityData);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private String getCityNameFromArguments(String[] args) {
        return args[0];
    }

    private void validateInputArguments(String[] args) {
        if(args.length == 0) {
            throw new RuntimeException("You must pass city name as a first argument");
        } else if(args.length > 1) {
            throw new RuntimeException("Application accept only one argument with city name");
        } else if(StringUtils.isEmpty(args[0])) {
            throw new RuntimeException("City name cannot be empty");
        }
    }
}
