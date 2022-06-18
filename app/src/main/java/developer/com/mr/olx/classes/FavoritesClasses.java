package developer.com.mr.olx.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class FavoritesClasses implements Serializable {

	@SerializedName("count")
	private int count;

	@SerializedName("count_label_next")
	private String countLabelNext;

	public int getCount(){
		return count;
	}

	public String getCountLabelNext(){
		return countLabelNext;
	}
}