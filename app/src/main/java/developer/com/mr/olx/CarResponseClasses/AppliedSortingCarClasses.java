package developer.com.mr.olx.CarResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppliedSortingCarClasses implements Serializable {

	@SerializedName("key")
	private String key;

	public String getKey(){
		return key;
	}
}