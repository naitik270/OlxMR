package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsSellProperty implements Serializable {

    @SerializedName("property_inventory_id")
    @Expose
    private Integer propertyInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("p_title")
    @Expose
    private String pTitle;
    @SerializedName("p_type_id")
    @Expose
    private Integer pTypeId;
    @SerializedName("p_name")
    @Expose
    private String pName;
    @SerializedName("p_bedroom")
    @Expose
    private Integer pBedroom;
    @SerializedName("p_bathroom")
    @Expose
    private Integer pBathroom;
    @SerializedName("p_sq_ft")
    @Expose
    private Integer pSqFt;
    @SerializedName("p_price")
    @Expose
    private Double pPrice;
    @SerializedName("p_picture_link_1")
    @Expose
    private String pPictureLink1;
    @SerializedName("p_picture_link_2")
    @Expose
    private String pPictureLink2;
    @SerializedName("p_picture_link_3")
    @Expose
    private String pPictureLink3;
    @SerializedName("p_picture_link_4")
    @Expose
    private String pPictureLink4;
    @SerializedName("p_picture_link_5")
    @Expose
    private String pPictureLink5;
    @SerializedName("p_picture_link_6")
    @Expose
    private String pPictureLink6;
    @SerializedName("p_picture_link_7")
    @Expose
    private String pPictureLink7;
    @SerializedName("p_picture_link_8")
    @Expose
    private String pPictureLink8;
    @SerializedName("p_picture_link_9")
    @Expose
    private String pPictureLink9;
    @SerializedName("p_picture_link_10")
    @Expose
    private String pPictureLink10;
    @SerializedName("p_furnishing_id")
    @Expose
    private Integer pFurnishingId;
    @SerializedName("p_car_parking_id")
    @Expose
    private Integer pCarParkingId;
    @SerializedName("p_sup_builtup_area")
    @Expose
    private Integer pSupBuiltupArea;
    @SerializedName("p_carpet_area")
    @Expose
    private Integer pCarpetArea;
    @SerializedName("p_listed_by_id")
    @Expose
    private Integer pListedById;
    @SerializedName("p_status")
    @Expose
    private String pStatus;
    @SerializedName("p_maintenance")
    @Expose
    private Integer pMaintenance;
    @SerializedName("p_show_mo_no")
    @Expose
    private Boolean pShowMoNo;
    @SerializedName("p_description")
    @Expose
    private String pDescription;
    @SerializedName("p_location")
    @Expose
    private String pLocation;
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

    public Integer getPropertyInventoryId() {
        return propertyInventoryId;
    }

    public void setPropertyInventoryId(Integer propertyInventoryId) {
        this.propertyInventoryId = propertyInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public Integer getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(Integer pTypeId) {
        this.pTypeId = pTypeId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getpBedroom() {
        return pBedroom;
    }

    public void setpBedroom(Integer pBedroom) {
        this.pBedroom = pBedroom;
    }

    public Integer getpBathroom() {
        return pBathroom;
    }

    public void setpBathroom(Integer pBathroom) {
        this.pBathroom = pBathroom;
    }

    public Integer getpSqFt() {
        return pSqFt;
    }

    public void setpSqFt(Integer pSqFt) {
        this.pSqFt = pSqFt;
    }

    public Double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpPictureLink1() {
        return pPictureLink1;
    }

    public void setpPictureLink1(String pPictureLink1) {
        this.pPictureLink1 = pPictureLink1;
    }

    public String getpPictureLink2() {
        return pPictureLink2;
    }

    public void setpPictureLink2(String pPictureLink2) {
        this.pPictureLink2 = pPictureLink2;
    }

    public String getpPictureLink3() {
        return pPictureLink3;
    }

    public void setpPictureLink3(String pPictureLink3) {
        this.pPictureLink3 = pPictureLink3;
    }

    public String getpPictureLink4() {
        return pPictureLink4;
    }

    public void setpPictureLink4(String pPictureLink4) {
        this.pPictureLink4 = pPictureLink4;
    }

    public String getpPictureLink5() {
        return pPictureLink5;
    }

    public void setpPictureLink5(String pPictureLink5) {
        this.pPictureLink5 = pPictureLink5;
    }

    public String getpPictureLink6() {
        return pPictureLink6;
    }

    public void setpPictureLink6(String pPictureLink6) {
        this.pPictureLink6 = pPictureLink6;
    }

    public String getpPictureLink7() {
        return pPictureLink7;
    }

    public void setpPictureLink7(String pPictureLink7) {
        this.pPictureLink7 = pPictureLink7;
    }

    public String getpPictureLink8() {
        return pPictureLink8;
    }

    public void setpPictureLink8(String pPictureLink8) {
        this.pPictureLink8 = pPictureLink8;
    }

    public String getpPictureLink9() {
        return pPictureLink9;
    }

    public void setpPictureLink9(String pPictureLink9) {
        this.pPictureLink9 = pPictureLink9;
    }

    public String getpPictureLink10() {
        return pPictureLink10;
    }

    public void setpPictureLink10(String pPictureLink10) {
        this.pPictureLink10 = pPictureLink10;
    }

    public Integer getpFurnishingId() {
        return pFurnishingId;
    }

    public void setpFurnishingId(Integer pFurnishingId) {
        this.pFurnishingId = pFurnishingId;
    }

    public Integer getpCarParkingId() {
        return pCarParkingId;
    }

    public void setpCarParkingId(Integer pCarParkingId) {
        this.pCarParkingId = pCarParkingId;
    }

    public Integer getpSupBuiltupArea() {
        return pSupBuiltupArea;
    }

    public void setpSupBuiltupArea(Integer pSupBuiltupArea) {
        this.pSupBuiltupArea = pSupBuiltupArea;
    }

    public Integer getpCarpetArea() {
        return pCarpetArea;
    }

    public void setpCarpetArea(Integer pCarpetArea) {
        this.pCarpetArea = pCarpetArea;
    }

    public Integer getpListedById() {
        return pListedById;
    }

    public void setpListedById(Integer pListedById) {
        this.pListedById = pListedById;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public Integer getpMaintenance() {
        return pMaintenance;
    }

    public void setpMaintenance(Integer pMaintenance) {
        this.pMaintenance = pMaintenance;
    }

    public Boolean getpShowMoNo() {
        return pShowMoNo;
    }

    public void setpShowMoNo(Boolean pShowMoNo) {
        this.pShowMoNo = pShowMoNo;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
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

