package pl.impaq.test.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andrew on 28.09.16.
 */
public class CityData {
    @SerializedName("_id")
    private String id;
    private String name;
    private String type;
    @SerializedName("geo_position")
    private GeoPosition geoPosition;

    public CityData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    @Override
    public String toString() {
        return "CityData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", geoPosition=" + geoPosition +
                '}';
    }
}
