package developer.com.mr.olx.AllInOneResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppliedSortingClasses implements Serializable {

	@SerializedName("key")
	private String key;

	public String getKey(){
		return key;
	}
}