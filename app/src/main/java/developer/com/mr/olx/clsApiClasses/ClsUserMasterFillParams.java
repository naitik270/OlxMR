package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsUserMasterFillParams {
    int login_id;

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
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
