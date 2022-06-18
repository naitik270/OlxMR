package developer.com.mr.olx.AllInOneResponseClasses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import developer.com.mr.olx.classes.ValuesClasses;


public class AppliedFiltersClasses implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("description")
	private String description;

	@SerializedName("display_order")
	private int displayOrder;

	@SerializedName("type")
	private String type;

	@SerializedName("render_as")
	private String renderAs;

	@SerializedName("values")
	private List<ValuesClasses> values;

	public String getId(){
		return id;
	}

	public String getDescription(){
		return description;
	}

	public int getDisplayOrder(){
		return displayOrder;
	}

	public String getType(){
		return type;
	}

	public String getRenderAs(){
		return renderAs;
	}

	public List<ValuesClasses> getValues(){
		return values;
	}
}