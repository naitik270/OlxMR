package developer.com.mr.olx.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JsonMemberPackageClasses implements Serializable {

	@SerializedName("id")
	private String id;

	public String getId(){
		return id;
	}
}