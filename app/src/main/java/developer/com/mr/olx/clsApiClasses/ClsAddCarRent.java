package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsAddCarRent implements Serializable {

    @SerializedName("car_rent_inventory_id")
    @Expose
    private Integer carRentInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("car_title")
    @Expose
    private String carTitle;
    @SerializedName("c_price")
    @Expose
    private Double cPrice;
    @SerializedName("c_picture_link_1")
    @Expose
    private String cPictureLink1;
    @SerializedName("c_picture_link_2")
    @Expose
    private String cPictureLink2;
    @SerializedName("c_picture_link_3")
    @Expose
    private String cPictureLink3;
    @SerializedName("c_picture_link_4")
    @Expose
    private String cPictureLink4;
    @SerializedName("c_picture_link_5")
    @Expose
    private String cPictureLink5;
    @SerializedName("c_picture_link_6")
    @Expose
    private String cPictureLink6;
    @SerializedName("c_picture_link_7")
    @Expose
    private String cPictureLink7;
    @SerializedName("c_picture_link_8")
    @Expose
    private String cPictureLink8;
    @SerializedName("c_picture_link_9")
    @Expose
    private String cPictureLink9;
    @SerializedName("c_picture_link_10")
    @Expose
    private String cPictureLink10;
    @SerializedName("c_km_limit")
    @Expose
    private Double cKmLimit;
    @SerializedName("c_car_type")
    @Expose
    private String cCarType;
    @SerializedName("c_extra_charge")
    @Expose
    private Integer cExtraCharge;
    @SerializedName("c_per_day_charge")
    @Expose
    private Integer cPerDayCharge;
    @SerializedName("car_fuel_id")
    @Expose
    private Integer carFuelId;
    @SerializedName("c_with_fuel")
    @Expose
    private Boolean cWithFuel;
    @SerializedName("c_driver")
    @Expose
    private Boolean cDriver;
    @SerializedName("c_transmission")
    @Expose
    private Boolean cTransmission;
    @SerializedName("c_show_mo_no")
    @Expose
    private Boolean cShowMoNo;
    @SerializedName("c_description")
    @Expose
    private String cDescription;
    @SerializedName("c_location")
    @Expose
    private String cLocation;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("sub_cat_id")
    @Expose
    private Integer subCatId;

    public Integer getCarRentInventoryId() {
        return carRentInventoryId;
    }

    public void setCarRentInventoryId(Integer carRentInventoryId) {
        this.carRentInventoryId = carRentInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarTitle() {
        return carTitle;
    }

    public void setCarTitle(String carTitle) {
        this.carTitle = carTitle;
    }

    public Double getcPrice() {
        return cPrice;
    }

    public void setcPrice(Double cPrice) {
        this.cPrice = cPrice;
    }

    public String getcPictureLink1() {
        return cPictureLink1;
    }

    public void setcPictureLink1(String cPictureLink1) {
        this.cPictureLink1 = cPictureLink1;
    }

    public String getcPictureLink2() {
        return cPictureLink2;
    }

    public void setcPictureLink2(String cPictureLink2) {
        this.cPictureLink2 = cPictureLink2;
    }

    public String getcPictureLink3() {
        return cPictureLink3;
    }

    public void setcPictureLink3(String cPictureLink3) {
        this.cPictureLink3 = cPictureLink3;
    }

    public String getcPictureLink4() {
        return cPictureLink4;
    }

    public void setcPictureLink4(String cPictureLink4) {
        this.cPictureLink4 = cPictureLink4;
    }

    public String getcPictureLink5() {
        return cPictureLink5;
    }

    public void setcPictureLink5(String cPictureLink5) {
        this.cPictureLink5 = cPictureLink5;
    }

    public String getcPictureLink6() {
        return cPictureLink6;
    }

    public void setcPictureLink6(String cPictureLink6) {
        this.cPictureLink6 = cPictureLink6;
    }

    public String getcPictureLink7() {
        return cPictureLink7;
    }

    public void setcPictureLink7(String cPictureLink7) {
        this.cPictureLink7 = cPictureLink7;
    }

    public String getcPictureLink8() {
        return cPictureLink8;
    }

    public void setcPictureLink8(String cPictureLink8) {
        this.cPictureLink8 = cPictureLink8;
    }

    public String getcPictureLink9() {
        return cPictureLink9;
    }

    public void setcPictureLink9(String cPictureLink9) {
        this.cPictureLink9 = cPictureLink9;
    }

    public String getcPictureLink10() {
        return cPictureLink10;
    }

    public void setcPictureLink10(String cPictureLink10) {
        this.cPictureLink10 = cPictureLink10;
    }

    public Double getcKmLimit() {
        return cKmLimit;
    }

    public void setcKmLimit(Double cKmLimit) {
        this.cKmLimit = cKmLimit;
    }

    public String getcCarType() {
        return cCarType;
    }

    public void setcCarType(String cCarType) {
        this.cCarType = cCarType;
    }

    public Integer getcExtraCharge() {
        return cExtraCharge;
    }

    public void setcExtraCharge(Integer cExtraCharge) {
        this.cExtraCharge = cExtraCharge;
    }

    public Integer getcPerDayCharge() {
        return cPerDayCharge;
    }

    public void setcPerDayCharge(Integer cPerDayCharge) {
        this.cPerDayCharge = cPerDayCharge;
    }

    public Integer getCarFuelId() {
        return carFuelId;
    }

    public void setCarFuelId(Integer carFuelId) {
        this.carFuelId = carFuelId;
    }

    public Boolean getcWithFuel() {
        return cWithFuel;
    }

    public void setcWithFuel(Boolean cWithFuel) {
        this.cWithFuel = cWithFuel;
    }

    public Boolean getcDriver() {
        return cDriver;
    }

    public void setcDriver(Boolean cDriver) {
        this.cDriver = cDriver;
    }

    public Boolean getcTransmission() {
        return cTransmission;
    }

    public void setcTransmission(Boolean cTransmission) {
        this.cTransmission = cTransmission;
    }

    public Boolean getcShowMoNo() {
        return cShowMoNo;
    }

    public void setcShowMoNo(Boolean cShowMoNo) {
        this.cShowMoNo = cShowMoNo;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }
    @SerializedName("success")
    @Expose
    private Integer success;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }


}

