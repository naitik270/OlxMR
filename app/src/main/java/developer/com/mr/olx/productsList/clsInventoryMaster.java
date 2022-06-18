package developer.com.mr.olx.productsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class clsInventoryMaster {

    @SerializedName("all_inventory_id")
    @Expose
    private Integer allInventoryId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("sub_cat_id")
    @Expose
    private Integer subCatId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("picture_link_1")
    @Expose
    private String pictureLink1;
    @SerializedName("picture_link_2")
    @Expose
    private String pictureLink2;
    @SerializedName("picture_link_3")
    @Expose
    private String pictureLink3;
    @SerializedName("picture_link_4")
    @Expose
    private String pictureLink4;
    @SerializedName("picture_link_5")
    @Expose
    private String pictureLink5;
    @SerializedName("picture_link_6")
    @Expose
    private String pictureLink6;
    @SerializedName("picture_link_7")
    @Expose
    private String pictureLink7;
    @SerializedName("picture_link_8")
    @Expose
    private String pictureLink8;
    @SerializedName("picture_link_9")
    @Expose
    private String pictureLink9;
    @SerializedName("picture_link_10")
    @Expose
    private String pictureLink10;
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

    public Integer getAllInventoryId() {
        return allInventoryId;
    }

    public void setAllInventoryId(Integer allInventoryId) {
        this.allInventoryId = allInventoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPictureLink1() {
        return pictureLink1;
    }

    public void setPictureLink1(String pictureLink1) {
        this.pictureLink1 = pictureLink1;
    }

    public String getPictureLink2() {
        return pictureLink2;
    }

    public void setPictureLink2(String pictureLink2) {
        this.pictureLink2 = pictureLink2;
    }

    public String getPictureLink3() {
        return pictureLink3;
    }

    public void setPictureLink3(String pictureLink3) {
        this.pictureLink3 = pictureLink3;
    }

    public String getPictureLink4() {
        return pictureLink4;
    }

    public void setPictureLink4(String pictureLink4) {
        this.pictureLink4 = pictureLink4;
    }

    public String getPictureLink5() {
        return pictureLink5;
    }

    public void setPictureLink5(String pictureLink5) {
        this.pictureLink5 = pictureLink5;
    }

    public String getPictureLink6() {
        return pictureLink6;
    }

    public void setPictureLink6(String pictureLink6) {
        this.pictureLink6 = pictureLink6;
    }

    public String getPictureLink7() {
        return pictureLink7;
    }

    public void setPictureLink7(String pictureLink7) {
        this.pictureLink7 = pictureLink7;
    }

    public String getPictureLink8() {
        return pictureLink8;
    }

    public void setPictureLink8(String pictureLink8) {
        this.pictureLink8 = pictureLink8;
    }

    public String getPictureLink9() {
        return pictureLink9;
    }

    public void setPictureLink9(String pictureLink9) {
        this.pictureLink9 = pictureLink9;
    }

    public String getPictureLink10() {
        return pictureLink10;
    }

    public void setPictureLink10(String pictureLink10) {
        this.pictureLink10 = pictureLink10;
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
