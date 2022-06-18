package developer.com.mr.olx.productsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsInventoryMasterFormData {

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
