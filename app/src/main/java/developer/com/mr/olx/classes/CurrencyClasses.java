package developer.com.mr.olx.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrencyClasses implements Serializable {

	@SerializedName("iso_4217")
	private String iso4217;

	@SerializedName("pre")
	private String pre;

	public String getIso4217(){
		return iso4217;
	}

	public String getPre(){
		return pre;
	}
}