package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsOffice implements Serializable {

    @SerializedName("office_inventory_id")
    @Expose
    private Integer officeInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("o_title")
    @Expose
    private String oTitle;
    @SerializedName("p_type_id")
    @Expose
    private Integer pTypeId;
    @SerializedName("p_name")
    @Expose
    private String pName;
    @SerializedName("cabin")
    @Expose
    private Integer cabin;
    @SerializedName("washroom")
    @Expose
    private Integer washroom;
    @SerializedName("o_sq_ft")
    @Expose
    private Integer oSqFt;
    @SerializedName("o_price")
    @Expose
    private Double oPrice;
    @SerializedName("o_picture_link_1")
    @Expose
    private String oPictureLink1;
    @SerializedName("o_picture_link_2")
    @Expose
    private String oPictureLink2;
    @SerializedName("o_picture_link_3")
    @Expose
    private String oPictureLink3;
    @SerializedName("o_picture_link_4")
    @Expose
    private String oPictureLink4;
    @SerializedName("o_picture_link_5")
    @Expose
    private String oPictureLink5;
    @SerializedName("o_picture_link_6")
    @Expose
    private String oPictureLink6;
    @SerializedName("o_picture_link_7")
    @Expose
    private String oPictureLink7;
    @SerializedName("o_picture_link_8")
    @Expose
    private String oPictureLink8;
    @SerializedName("o_picture_link_9")
    @Expose
    private String oPictureLink9;
    @SerializedName("o_picture_link_10")
    @Expose
    private String oPictureLink10;
    @SerializedName("p_furnishing_id")
    @Expose
    private Integer pFurnishingId;
    @SerializedName("p_car_parking_id")
    @Expose
    private Integer pCarParkingId;
    @SerializedName("o_sup_builtup_area")
    @Expose
    private Integer oSupBuiltupArea;
    @SerializedName("o_carpet_area")
    @Expose
    private Integer oCarpetArea;
    @SerializedName("p_listed_by_id")
    @Expose
    private Integer pListedById;
    @SerializedName("employee_capacity")
    @Expose
    private Integer employeeCapacity;
    @SerializedName("o_maintenance")
    @Expose
    private Integer oMaintenance;
    @SerializedName("o_show_mo_no")
    @Expose
    private Boolean oShowMoNo;
    @SerializedName("o_description")
    @Expose
    private String oDescription;
    @SerializedName("o_location")
    @Expose
    private String oLocation;
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

    public Integer getOfficeInventoryId() {
        return officeInventoryId;
    }

    public void setOfficeInventoryId(Integer officeInventoryId) {
        this.officeInventoryId = officeInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getoTitle() {
        return oTitle;
    }

    public void setoTitle(String oTitle) {
        this.oTitle = oTitle;
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

    public Integer getCabin() {
        return cabin;
    }

    public void setCabin(Integer cabin) {
        this.cabin = cabin;
    }

    public Integer getWashroom() {
        return washroom;
    }

    public void setWashroom(Integer washroom) {
        this.washroom = washroom;
    }

    public Integer getoSqFt() {
        return oSqFt;
    }

    public void setoSqFt(Integer oSqFt) {
        this.oSqFt = oSqFt;
    }

    public Double getoPrice() {
        return oPrice;
    }

    public void setoPrice(Double oPrice) {
        this.oPrice = oPrice;
    }

    public String getoPictureLink1() {
        return oPictureLink1;
    }

    public void setoPictureLink1(String oPictureLink1) {
        this.oPictureLink1 = oPictureLink1;
    }

    public String getoPictureLink2() {
        return oPictureLink2;
    }

    public void setoPictureLink2(String oPictureLink2) {
        this.oPictureLink2 = oPictureLink2;
    }

    public String getoPictureLink3() {
        return oPictureLink3;
    }

    public void setoPictureLink3(String oPictureLink3) {
        this.oPictureLink3 = oPictureLink3;
    }

    public String getoPictureLink4() {
        return oPictureLink4;
    }

    public void setoPictureLink4(String oPictureLink4) {
        this.oPictureLink4 = oPictureLink4;
    }

    public String getoPictureLink5() {
        return oPictureLink5;
    }

    public void setoPictureLink5(String oPictureLink5) {
        this.oPictureLink5 = oPictureLink5;
    }

    public String getoPictureLink6() {
        return oPictureLink6;
    }

    public void setoPictureLink6(String oPictureLink6) {
        this.oPictureLink6 = oPictureLink6;
    }

    public String getoPictureLink7() {
        return oPictureLink7;
    }

    public void setoPictureLink7(String oPictureLink7) {
        this.oPictureLink7 = oPictureLink7;
    }

    public String getoPictureLink8() {
        return oPictureLink8;
    }

    public void setoPictureLink8(String oPictureLink8) {
        this.oPictureLink8 = oPictureLink8;
    }

    public String getoPictureLink9() {
        return oPictureLink9;
    }

    public void setoPictureLink9(String oPictureLink9) {
        this.oPictureLink9 = oPictureLink9;
    }

    public String getoPictureLink10() {
        return oPictureLink10;
    }

    public void setoPictureLink10(String oPictureLink10) {
        this.oPictureLink10 = oPictureLink10;
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

    public Integer getoSupBuiltupArea() {
        return oSupBuiltupArea;
    }

    public void setoSupBuiltupArea(Integer oSupBuiltupArea) {
        this.oSupBuiltupArea = oSupBuiltupArea;
    }

    public Integer getoCarpetArea() {
        return oCarpetArea;
    }

    public void setoCarpetArea(Integer oCarpetArea) {
        this.oCarpetArea = oCarpetArea;
    }

    public Integer getpListedById() {
        return pListedById;
    }

    public void setpListedById(Integer pListedById) {
        this.pListedById = pListedById;
    }

    public Integer getEmployeeCapacity() {
        return employeeCapacity;
    }

    public void setEmployeeCapacity(Integer employeeCapacity) {
        this.employeeCapacity = employeeCapacity;
    }

    public Integer getoMaintenance() {
        return oMaintenance;
    }

    public void setoMaintenance(Integer oMaintenance) {
        this.oMaintenance = oMaintenance;
    }

    public Boolean getoShowMoNo() {
        return oShowMoNo;
    }

    public void setoShowMoNo(Boolean oShowMoNo) {
        this.oShowMoNo = oShowMoNo;
    }

    public String getoDescription() {
        return oDescription;
    }

    public void setoDescription(String oDescription) {
        this.oDescription = oDescription;
    }

    public String getoLocation() {
        return oLocation;
    }

    public void setoLocation(String oLocation) {
        this.oLocation = oLocation;
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

