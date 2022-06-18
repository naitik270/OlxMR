package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsLoginWithPhoneParams implements Serializable {

    String mobile_no = "";
    String mobile_cc_code = "";

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getMobile_cc_code() {
        return mobile_cc_code;
    }

    public void setMobile_cc_code(String mobile_cc_code) {
        this.mobile_cc_code = mobile_cc_code;
    }

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<ClsLoginWithPhoneResponse> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<ClsLoginWithPhoneResponse> getData() {
        return data;
    }

    public void setData(List<ClsLoginWithPhoneResponse> data) {
        this.data = data;
    }
}
