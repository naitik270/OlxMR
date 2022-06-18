package developer.com.mr.olx.userMaster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsUserMaster {

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
    private Boolean isVerify;
    @SerializedName("profile_picture_base64url")
    @Expose
    private String profilePictureBase64url;

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

    public Boolean getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Boolean isVerify) {
        this.isVerify = isVerify;
    }

    public String getProfilePictureBase64url() {
        return profilePictureBase64url;
    }

    public void setProfilePictureBase64url(String profilePictureBase64url) {
        this.profilePictureBase64url = profilePictureBase64url;
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
