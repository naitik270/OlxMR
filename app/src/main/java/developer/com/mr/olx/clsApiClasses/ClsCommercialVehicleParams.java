package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsCommercialVehicleParams implements Serializable {


    @SerializedName("vehicel_inventory_id")
    @Expose
    private Integer vehicelInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("v_title")
    @Expose
    private String vTitle;
    @SerializedName("v_price")
    @Expose
    private Double vPrice;
    @SerializedName("v_picture_link_1")
    @Expose
    private String vPictureLink1;
    @SerializedName("v_picture_link_2")
    @Expose
    private String vPictureLink2;
    @SerializedName("v_picture_link_3")
    @Expose
    private String vPictureLink3;
    @SerializedName("v_picture_link_4")
    @Expose
    private String vPictureLink4;
    @SerializedName("v_picture_link_5")
    @Expose
    private String vPictureLink5;
    @SerializedName("v_picture_link_6")
    @Expose
    private String vPictureLink6;
    @SerializedName("v_picture_link_7")
    @Expose
    private String vPictureLink7;
    @SerializedName("v_picture_link_8")
    @Expose
    private String vPictureLink8;
    @SerializedName("v_picture_link_9")
    @Expose
    private String vPictureLink9;
    @SerializedName("v_picture_link_10")
    @Expose
    private String vPictureLink10;
    @SerializedName("v_type_id")
    @Expose
    private Integer vTypeId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("kms")
    @Expose
    private Integer kms;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("insuarance")
    @Expose
    private Boolean insuarance;
    @SerializedName("i_description")
    @Expose
    private String iDescription;
    @SerializedName("v_show_mo_no")
    @Expose
    private Boolean vShowMoNo;
    @SerializedName("v_description")
    @Expose
    private String vDescription;
    @SerializedName("v_location")
    @Expose
    private String vLocation;
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

    public Integer getVehicelInventoryId() {
        return vehicelInventoryId;
    }

    public void setVehicelInventoryId(Integer vehicelInventoryId) {
        this.vehicelInventoryId = vehicelInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getvTitle() {
        return vTitle;
    }

    public void setvTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public Double getvPrice() {
        return vPrice;
    }

    public void setvPrice(Double vPrice) {
        this.vPrice = vPrice;
    }

    public String getvPictureLink1() {
        return vPictureLink1;
    }

    public void setvPictureLink1(String vPictureLink1) {
        this.vPictureLink1 = vPictureLink1;
    }

    public String getvPictureLink2() {
        return vPictureLink2;
    }

    public void setvPictureLink2(String vPictureLink2) {
        this.vPictureLink2 = vPictureLink2;
    }

    public String getvPictureLink3() {
        return vPictureLink3;
    }

    public void setvPictureLink3(String vPictureLink3) {
        this.vPictureLink3 = vPictureLink3;
    }

    public String getvPictureLink4() {
        return vPictureLink4;
    }

    public void setvPictureLink4(String vPictureLink4) {
        this.vPictureLink4 = vPictureLink4;
    }

    public String getvPictureLink5() {
        return vPictureLink5;
    }

    public void setvPictureLink5(String vPictureLink5) {
        this.vPictureLink5 = vPictureLink5;
    }

    public String getvPictureLink6() {
        return vPictureLink6;
    }

    public void setvPictureLink6(String vPictureLink6) {
        this.vPictureLink6 = vPictureLink6;
    }

    public String getvPictureLink7() {
        return vPictureLink7;
    }

    public void setvPictureLink7(String vPictureLink7) {
        this.vPictureLink7 = vPictureLink7;
    }

    public String getvPictureLink8() {
        return vPictureLink8;
    }

    public void setvPictureLink8(String vPictureLink8) {
        this.vPictureLink8 = vPictureLink8;
    }

    public String getvPictureLink9() {
        return vPictureLink9;
    }

    public void setvPictureLink9(String vPictureLink9) {
        this.vPictureLink9 = vPictureLink9;
    }

    public String getvPictureLink10() {
        return vPictureLink10;
    }

    public void setvPictureLink10(String vPictureLink10) {
        this.vPictureLink10 = vPictureLink10;
    }

    public Integer getvTypeId() {
        return vTypeId;
    }

    public void setvTypeId(Integer vTypeId) {
        this.vTypeId = vTypeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getInsuarance() {
        return insuarance;
    }

    public void setInsuarance(Boolean insuarance) {
        this.insuarance = insuarance;
    }

    public String getiDescription() {
        return iDescription;
    }

    public void setiDescription(String iDescription) {
        this.iDescription = iDescription;
    }

    public Boolean getvShowMoNo() {
        return vShowMoNo;
    }

    public void setvShowMoNo(Boolean vShowMoNo) {
        this.vShowMoNo = vShowMoNo;
    }

    public String getvDescription() {
        return vDescription;
    }

    public void setvDescription(String vDescription) {
        this.vDescription = vDescription;
    }

    public String getvLocation() {
        return vLocation;
    }

    public void setvLocation(String vLocation) {
        this.vLocation = vLocation;
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
