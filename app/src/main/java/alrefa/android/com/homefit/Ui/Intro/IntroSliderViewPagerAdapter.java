package alrefa.android.com.homefit.Ui.Intro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class IntroSliderViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public IntroSliderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {

            case 0:
                return fragments.get(0);

            case 1:
                return fragments.get(1);

            case 2:
                return fragments.get(2);

            case 3:
                return fragments.get(3);

            default:
                return fragments.get(0);
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

}
