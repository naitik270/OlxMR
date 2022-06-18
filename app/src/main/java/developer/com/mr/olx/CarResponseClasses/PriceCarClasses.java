package developer.com.mr.olx.CarResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PriceCarClasses implements Serializable {

	@SerializedName("value")
	private ValueCarClasses value;

	@SerializedName("key_name")
	private String keyName;

	@SerializedName("key")
	private String key;

	public ValueCarClasses getValue(){
		return value;
	}

	public String getKeyName(){
		return keyName;
	}

	public String getKey(){
		return key;
	}
}