package developer.com.mr.olx.CarResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusCarClasses implements Serializable {

	@SerializedName("status")
	private String status;

	@SerializedName("allow_edit")
	private boolean allowEdit;

	@SerializedName("ban_reason_id")
	private Object banReasonId;

	@SerializedName("display")
	private String display;

	@SerializedName("translated_display")
	private String translatedDisplay;

	@SerializedName("link")
	private Object link;

	@SerializedName("flags")
	private FlagsCarClasses flags;

	@SerializedName("message")
	private Object message;

	public String getStatus(){
		return status;
	}

	public boolean isAllowEdit(){
		return allowEdit;
	}

	public Object getBanReasonId(){
		return banReasonId;
	}

	public String getDisplay(){
		return display;
	}

	public String getTranslatedDisplay(){
		return translatedDisplay;
	}

	public Object getLink(){
		return link;
	}

	public FlagsCarClasses getFlags(){
		return flags;
	}

	public Object getMessage(){
		return message;
	}
}