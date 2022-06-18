package developer.com.mr.olx.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import developer.com.mr.olx.fragments.ImageFragment1;
import developer.com.mr.olx.fragments.ImageFragment2;


public class ImageViewPagerAdapter extends FragmentStatePagerAdapter {
    private String image1, image2;

    public ImageViewPagerAdapter(@NonNull   FragmentManager fm, int behavior, String image1, String image2) {
        super(fm, behavior);
        this.image1 = image1;
        this.image2 = image2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return ImageFragment1.newInstance(image1);
            case 1:
                return ImageFragment2.newInstance(image2);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
