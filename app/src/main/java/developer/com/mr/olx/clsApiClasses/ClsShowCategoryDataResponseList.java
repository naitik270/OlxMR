package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClsShowCategoryDataResponseList implements Serializable {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("Sub_category")
    @Expose
    private String subCategory;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("from_salary")
    @Expose
    private Integer fromSalary;
    @SerializedName("to_salary")
    @Expose
    private Integer toSalary;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("distance_in_km")
    @Expose
    private Integer distanceInKm;
    @SerializedName("Create_Date")
    @Expose
    private String createDate;
    @SerializedName("Modify_Date")
    @Expose
    private Object modifyDate;
    @SerializedName("car_year_of_registration")
    @Expose
    private Integer carYearOfRegistration;
    @SerializedName("car_km")
    @Expose
    private Integer carKm;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("Bedroom")
    @Expose
    private Integer bedroom;
    @SerializedName("Bathroom")
    @Expose
    private Integer bathroom;
    @SerializedName("Cabin")
    @Expose
    private Integer cabin;
    @SerializedName("Washroom")
    @Expose
    private Integer washroom;
    @SerializedName("Square_Feet")
    @Expose
    private Integer squareFeet;
    @SerializedName("Mobile_ram")
    @Expose
    private String mobileRam;
    @SerializedName("Mobile_rom")
    @Expose
    private String mobileRom;
    @SerializedName("Mobile_brand")
    @Expose
    private Object mobileBrand;
    @SerializedName("Mobile_colour")
    @Expose
    private String mobileColour;
    @SerializedName("year_of_registration")
    @Expose
    private Integer yearOfRegistration;
    @SerializedName("km")
    @Expose
    private Integer km;
    @SerializedName("Job_type")
    @Expose
    private String jobType;
    @SerializedName("Job_salary_period")
    @Expose
    private String jobSalaryPeriod;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFromSalary() {
        return fromSalary;
    }

    public void setFromSalary(Integer fromSalary) {
        this.fromSalary = fromSalary;
    }

    public Integer getToSalary() {
        return toSalary;
    }

    public void setToSalary(Integer toSalary) {
        this.toSalary = toSalary;
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

    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Object modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getCarYearOfRegistration() {
        return carYearOfRegistration;
    }

    public void setCarYearOfRegistration(Integer carYearOfRegistration) {
        this.carYearOfRegistration = carYearOfRegistration;
    }

    public Integer getCarKm() {
        return carKm;
    }

    public void setCarKm(Integer carKm) {
        this.carKm = carKm;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Integer getBathroom() {
        return bathroom;
    }

    public void setBathroom(Integer bathroom) {
        this.bathroom = bathroom;
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

    public Integer getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getMobileRam() {
        return mobileRam;
    }

    public void setMobileRam(String mobileRam) {
        this.mobileRam = mobileRam;
    }

    public String getMobileRom() {
        return mobileRom;
    }

    public void setMobileRom(String mobileRom) {
        this.mobileRom = mobileRom;
    }

    public Object getMobileBrand() {
        return mobileBrand;
    }

    public void setMobileBrand(Object mobileBrand) {
        this.mobileBrand = mobileBrand;
    }

    public String getMobileColour() {
        return mobileColour;
    }

    public void setMobileColour(String mobileColour) {
        this.mobileColour = mobileColour;
    }

    public Integer getYearOfRegistration() {
        return yearOfRegistration;
    }

    public void setYearOfRegistration(Integer yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobSalaryPeriod() {
        return jobSalaryPeriod;
    }

    public void setJobSalaryPeriod(String jobSalaryPeriod) {
        this.jobSalaryPeriod = jobSalaryPeriod;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
