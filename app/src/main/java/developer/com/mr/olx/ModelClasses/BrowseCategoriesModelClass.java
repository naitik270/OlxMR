package developer.com.mr.olx.ModelClasses;

public class BrowseCategoriesModelClass {

    private int image;

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    private String image_name;

    public int getPos() {
        return pos;
    }

    private int pos;

    public BrowseCategoriesModelClass(int image, int pos,String image_name) {
        this.image = image;
        this.pos = pos;
        this.image_name = image_name;
    }

    public int getImage() {
        return image;
    }
}
