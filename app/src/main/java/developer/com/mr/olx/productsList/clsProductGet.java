package developer.com.mr.olx.productsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class clsProductGet {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("category")
    @Expose
    private clsCategory category;
    @SerializedName("sold")
    @Expose
    private Integer sold;
    @SerializedName("user")
    @Expose
    private clsUser user;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public clsCategory getCategory() {
        return category;
    }

    public void setCategory(clsCategory category) {
        this.category = category;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public clsUser getUser() {
        return user;
    }

    public void setUser(clsUser user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
