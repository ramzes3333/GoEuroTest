package pl.impaq.test.businessLogic;

import pl.impaq.test.model.CityData;
import pl.impaq.test.model.GeoPosition;
import pl.impaq.test.utils.CsvWriter;
import pl.impaq.test.utils.PropertiesLoader;

import java.util.Arrays;

/**
 * Created by andrew on 28.09.16.
 */
public class CityDataCsvWriter {

    private String csvFileName;

    private static CityDataCsvWriter instance = null;

    protected CityDataCsvWriter() {
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        this.csvFileName = propertiesLoader.getPropertyValue("cityDataCsvFileName");
    }

    public static CityDataCsvWriter getInstance() {
        if(instance == null) {
            instance = new CityDataCsvWriter();
        }
        return instance;
    }

    public void write(CityData[] cityData) {
        if(cityData == null) {
            return;
        }

        CsvWriter csvWriter = new CsvWriter(csvFileName);
        csvWriter.openFile();

        Arrays.stream(cityData)
                   .forEach(cd -> csvWriter.writeLine(prepareDataForWriter(cd)));

        csvWriter.flush();
        csvWriter.close();
    }

    private String[] prepareDataForWriter(CityData cityElement) {
        GeoPosition geoPosition = cityElement.getGeoPosition();

        String[] elements = new String[5];

        elements[0] = cityElement.getId();
        elements[1] = cityElement.getName();
        elements[2] = cityElement.getType();
        elements[3] = geoPosition.getLatitude();
        elements[4] = geoPosition.getLongitude();

        return elements;
    }
}
