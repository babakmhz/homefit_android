package alrefa.android.com.homefit.Ui.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SliderPagerAdapter extends FragmentPagerAdapter {


    private final List<String> urls;


    public SliderPagerAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;


    }

    @Override
    public int getCount() {
        return 1;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                BannerSliderFragment fragment = BannerSliderFragment.newInstance();
                fragment.setImage(i);
                return fragment;
            case 1:
                return BannerSliderFragment.newInstance();
            case 2:
                return BannerSliderFragment.newInstance();
            case 3:
                return BannerSliderFragment.newInstance();
            default:
                return BannerSliderFragment.newInstance();
        }
    }


}
