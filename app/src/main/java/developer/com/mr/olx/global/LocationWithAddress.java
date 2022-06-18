package developer.com.mr.olx.global;

public class LocationWithAddress {

    Double Latitude;
    Double Longitude;
    String address;
    Boolean isFakeLocationEnable = false;


    public LocationWithAddress(Double latitude, Double longitude, String address
            , Boolean isFakeLocationEnable) {
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.address = address;
        this.isFakeLocationEnable = isFakeLocationEnable;
    }


    public LocationWithAddress(Double latitude, Double longitude, String address) {
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.address = address;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getFakeLocationEnable() {
        return isFakeLocationEnable;
    }

    public void setFakeLocationEnable(Boolean fakeLocationEnable) {
        isFakeLocationEnable = fakeLocationEnable;
    }


}
