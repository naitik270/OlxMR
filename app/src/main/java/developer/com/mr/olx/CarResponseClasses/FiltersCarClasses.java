package developer.com.mr.olx.CarResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FiltersCarClasses implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("values")
	private List<ValuesCarClasses> values;

	@SerializedName("display_order")
	private int displayOrder;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("render_as")
	private String renderAs;

	public String getId(){
		return id;
	}

	public List<ValuesCarClasses> getValues(){
		return values;
	}

	public int getDisplayOrder(){
		return displayOrder;
	}

	public String getDescription(){
		return description;
	}

	public String getType(){
		return type;
	}

	public String getRenderAs(){
		return renderAs;
	}
}