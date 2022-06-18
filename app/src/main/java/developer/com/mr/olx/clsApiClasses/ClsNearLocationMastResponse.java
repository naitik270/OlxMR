package developer.com.mr.olx.clsApiClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClsNearLocationMastResponse implements Serializable {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("data")
    @Expose
    private List<ClsNearLocationMasterList> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<ClsNearLocationMasterList> getData() {
        return data;
    }

    public void setData(List<ClsNearLocationMasterList> data) {
        this.data = data;
    }
}

