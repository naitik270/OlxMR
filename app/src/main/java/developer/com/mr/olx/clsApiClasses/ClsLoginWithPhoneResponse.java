package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsLoginWithPhoneResponse implements Serializable {

    @SerializedName("login_id")
    @Expose
    private Integer loginId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("mobile_cc_code")
    @Expose
    private String mobileCcCode;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("is_verify")
    @Expose
    private Integer isVerify;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileCcCode() {
        return mobileCcCode;
    }

    public void setMobileCcCode(String mobileCcCode) {
        this.mobileCcCode = mobileCcCode;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
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
