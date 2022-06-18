package developer.com.mr.olx.ModelClasses;

public class SliderItem {

    private String imageUrl;
    private int imageDrawable;

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SliderItem(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }
}
