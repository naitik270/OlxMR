package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsUserMasterFillResponse implements Serializable {

    @SerializedName("login_id")
    @Expose
    private Integer loginId;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("mail_id")
    @Expose
    private String mailId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bio_description")
    @Expose
    private String bioDescription;
    @SerializedName("profile_picture_link")
    @Expose
    private String profilePictureLink;
    @SerializedName("mobile_cc_code")
    @Expose
    private String mobileCcCode;
    @SerializedName("user_cat")
    @Expose
    private String userCat;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("is_verify")
    @Expose
    private Integer isVerify;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("modified_at")
    @Expose
    private Object modifiedAt;

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

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBioDescription() {
        return bioDescription;
    }

    public void setBioDescription(String bioDescription) {
        this.bioDescription = bioDescription;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public String getMobileCcCode() {
        return mobileCcCode;
    }

    public void setMobileCcCode(String mobileCcCode) {
        this.mobileCcCode = mobileCcCode;
    }

    public String getUserCat() {
        return userCat;
    }

    public void setUserCat(String userCat) {
        this.userCat = userCat;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Object modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<ClsUserMasterFillResponse> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<ClsUserMasterFillResponse> getData() {
        return data;
    }

    public void setData(List<ClsUserMasterFillResponse> data) {
        this.data = data;
    }

}
