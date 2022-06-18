package developer.com.mr.olx.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParametersClasses implements Serializable {

	@SerializedName("type")
	private String type;

	@SerializedName("key")
	private String key;

	@SerializedName("value")
	private String value;

	@SerializedName("key_name")
	private String keyName;

	@SerializedName("formatted_value")
	private String formattedValue;

	@SerializedName("value_name")
	private String valueName;

	public String getType(){
		return type;
	}

	public String getKey(){
		return key;
	}

	public String getValue(){
		return value;
	}

	public String getKeyName(){
		return keyName;
	}

	public String getFormattedValue(){
		return formattedValue;
	}

	public String getValueName(){
		return valueName;
	}
}