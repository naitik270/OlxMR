package developer.com.mr.olx.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlagsClasses implements Serializable {

	@SerializedName("new")
	private boolean jsonMemberNew;

	@SerializedName("hot")
	private boolean hot;

	public boolean isJsonMemberNew(){
		return jsonMemberNew;
	}

	public boolean isHot(){
		return hot;
	}
}