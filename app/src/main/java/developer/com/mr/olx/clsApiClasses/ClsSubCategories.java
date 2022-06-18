package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsSubCategories {

    @SerializedName("sub_cat_id")
    @Expose
    private Integer subCatId;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("sub_cat_name")
    @Expose
    private String subCatName;
    @SerializedName("cat_name")
    @Expose
    private String catName;

    public ClsSubCategories(Integer subCatId, Integer catId, String subCatName, String catName) {
        this.subCatId = subCatId;
        this.catId = catId;
        this.subCatName = subCatName;
        this.catName = catName;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public ClsSubCategories(String subCatName) {
        this.subCatName = subCatName;
    }

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<ClsSubCategories> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<ClsSubCategories> getData() {
        return data;
    }

    public void setData(List<ClsSubCategories> data) {
        this.data = data;
    }
}
