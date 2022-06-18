package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsAddMobileDetails implements Serializable {

    @SerializedName("mo_inventory_id")
    @Expose
    private Integer moInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("mo_title")
    @Expose
    private String moTitle;
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("model_name")
    @Expose
    private String modelName;
    @SerializedName("mo_ram")
    @Expose
    private String moRam;
    @SerializedName("mo_rom")
    @Expose
    private String moRom;
    @SerializedName("mo_colour")
    @Expose
    private String moColour;
    @SerializedName("mo_price")
    @Expose
    private Double moPrice;
    @SerializedName("mo_picture_link_1")
    @Expose
    private String moPictureLink1;
    @SerializedName("mo_picture_link_2")
    @Expose
    private String moPictureLink2;
    @SerializedName("mo_picture_link_3")
    @Expose
    private String moPictureLink3;
    @SerializedName("mo_picture_link_4")
    @Expose
    private String moPictureLink4;
    @SerializedName("mo_picture_link_5")
    @Expose
    private String moPictureLink5;
    @SerializedName("mo_picture_link_6")
    @Expose
    private String moPictureLink6;
    @SerializedName("mo_picture_link_7")
    @Expose
    private String moPictureLink7;
    @SerializedName("mo_picture_link_8")
    @Expose
    private String moPictureLink8;
    @SerializedName("mo_picture_link_9")
    @Expose
    private String moPictureLink9;
    @SerializedName("mo_picture_link_10")
    @Expose
    private String moPictureLink10;
    @SerializedName("mo_front_camera")
    @Expose
    private String moFrontCamera;
    @SerializedName("mo_rear_camera")
    @Expose
    private String moRearCamera;
    @SerializedName("mo_battery")
    @Expose
    private String moBattery;
    @SerializedName("mo_bill")
    @Expose
    private Boolean moBill;
    @SerializedName("mo_box")
    @Expose
    private Boolean moBox;
    @SerializedName("mo_charger")
    @Expose
    private Boolean moCharger;
    @SerializedName("mo_headset")
    @Expose
    private Boolean moHeadset;
    @SerializedName("mo_warranty")
    @Expose
    private Boolean moWarranty;
    @SerializedName("insurance")
    @Expose
    private Boolean insurance;
    @SerializedName("show_mo_no")
    @Expose
    private Boolean showMoNo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("location")
    @Expose
    private String location;
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

    public Integer getMoInventoryId() {
        return moInventoryId;
    }

    public void setMoInventoryId(Integer moInventoryId) {
        this.moInventoryId = moInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMoTitle() {
        return moTitle;
    }

    public void setMoTitle(String moTitle) {
        this.moTitle = moTitle;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMoRam() {
        return moRam;
    }

    public void setMoRam(String moRam) {
        this.moRam = moRam;
    }

    public String getMoRom() {
        return moRom;
    }

    public void setMoRom(String moRom) {
        this.moRom = moRom;
    }

    public String getMoColour() {
        return moColour;
    }

    public void setMoColour(String moColour) {
        this.moColour = moColour;
    }

    public Double getMoPrice() {
        return moPrice;
    }

    public void setMoPrice(Double moPrice) {
        this.moPrice = moPrice;
    }

    public String getMoPictureLink1() {
        return moPictureLink1;
    }

    public void setMoPictureLink1(String moPictureLink1) {
        this.moPictureLink1 = moPictureLink1;
    }

    public String getMoPictureLink2() {
        return moPictureLink2;
    }

    public void setMoPictureLink2(String moPictureLink2) {
        this.moPictureLink2 = moPictureLink2;
    }

    public String getMoPictureLink3() {
        return moPictureLink3;
    }

    public void setMoPictureLink3(String moPictureLink3) {
        this.moPictureLink3 = moPictureLink3;
    }

    public String getMoPictureLink4() {
        return moPictureLink4;
    }

    public void setMoPictureLink4(String moPictureLink4) {
        this.moPictureLink4 = moPictureLink4;
    }

    public String getMoPictureLink5() {
        return moPictureLink5;
    }

    public void setMoPictureLink5(String moPictureLink5) {
        this.moPictureLink5 = moPictureLink5;
    }

    public String getMoPictureLink6() {
        return moPictureLink6;
    }

    public void setMoPictureLink6(String moPictureLink6) {
        this.moPictureLink6 = moPictureLink6;
    }

    public String getMoPictureLink7() {
        return moPictureLink7;
    }

    public void setMoPictureLink7(String moPictureLink7) {
        this.moPictureLink7 = moPictureLink7;
    }

    public String getMoPictureLink8() {
        return moPictureLink8;
    }

    public void setMoPictureLink8(String moPictureLink8) {
        this.moPictureLink8 = moPictureLink8;
    }

    public String getMoPictureLink9() {
        return moPictureLink9;
    }

    public void setMoPictureLink9(String moPictureLink9) {
        this.moPictureLink9 = moPictureLink9;
    }

    public String getMoPictureLink10() {
        return moPictureLink10;
    }

    public void setMoPictureLink10(String moPictureLink10) {
        this.moPictureLink10 = moPictureLink10;
    }

    public String getMoFrontCamera() {
        return moFrontCamera;
    }

    public void setMoFrontCamera(String moFrontCamera) {
        this.moFrontCamera = moFrontCamera;
    }

    public String getMoRearCamera() {
        return moRearCamera;
    }

    public void setMoRearCamera(String moRearCamera) {
        this.moRearCamera = moRearCamera;
    }

    public String getMoBattery() {
        return moBattery;
    }

    public void setMoBattery(String moBattery) {
        this.moBattery = moBattery;
    }

    public Boolean getMoBill() {
        return moBill;
    }

    public void setMoBill(Boolean moBill) {
        this.moBill = moBill;
    }

    public Boolean getMoBox() {
        return moBox;
    }

    public void setMoBox(Boolean moBox) {
        this.moBox = moBox;
    }

    public Boolean getMoCharger() {
        return moCharger;
    }

    public void setMoCharger(Boolean moCharger) {
        this.moCharger = moCharger;
    }

    public Boolean getMoHeadset() {
        return moHeadset;
    }

    public void setMoHeadset(Boolean moHeadset) {
        this.moHeadset = moHeadset;
    }

    public Boolean getMoWarranty() {
        return moWarranty;
    }

    public void setMoWarranty(Boolean moWarranty) {
        this.moWarranty = moWarranty;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public Boolean getShowMoNo() {
        return showMoNo;
    }

    public void setShowMoNo(Boolean showMoNo) {
        this.showMoNo = showMoNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

