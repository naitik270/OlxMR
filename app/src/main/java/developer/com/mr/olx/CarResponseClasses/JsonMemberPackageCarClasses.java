package developer.com.mr.olx.CarResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JsonMemberPackageCarClasses implements Serializable {

	@SerializedName("id")
	private String id;

	public String getId(){
		return id;
	}
}