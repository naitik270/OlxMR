package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsShowCategoryDataParams {

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPrice_from(double price_from) {
        this.price_from = price_from;
    }

    public void setPrice_to(double price_to) {
        this.price_to = price_to;
    }

    public void setJob_salary_from(double job_salary_from) {
        this.job_salary_from = job_salary_from;
    }

    public void setJob_salary_to(double job_salary_to) {
        this.job_salary_to = job_salary_to;
    }

    public void setDistance_sorting(String distance_sorting) {
        this.distance_sorting = distance_sorting;
    }

    public void setDate_sorting(String date_sorting) {
        this.date_sorting = date_sorting;
    }

    public void setPrice_sorting(String price_sorting) {
        this.price_sorting = price_sorting;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    double latitude;
    double longitude;
    double price_from;
    double price_to;
    double job_salary_from;
    double job_salary_to;
    String distance_sorting = "";
    String date_sorting = "";
    String price_sorting = "";
    String sub_cat_id = "";
    String brand_id = "";
    int cat_id = 0;
    int limit = 0;
    int page_no = 0;


    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("data")
    @Expose
    private List<ClsShowCategoryDataResponseList> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<ClsShowCategoryDataResponseList> getData() {
        return data;
    }

    public void setData(List<ClsShowCategoryDataResponseList> data) {
        this.data = data;
    }
}
