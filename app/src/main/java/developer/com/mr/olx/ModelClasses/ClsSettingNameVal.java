package developer.com.mr.olx.ModelClasses;

public class ClsSettingNameVal {

    private int Images;

    public String getSettingName() {
        return SettingName;
    }

    public void setSettingName(String settingName) {
        SettingName = settingName;
    }

    private String SettingName ="";

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position = 0;

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }

    public ClsSettingNameVal(int ic_profile, String setting_profile,
                            int position) {
        this.Images = ic_profile;
        this.SettingName = setting_profile;
        this.position = position;
    }


}
