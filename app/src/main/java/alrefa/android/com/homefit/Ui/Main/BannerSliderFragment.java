package alrefa.android.com.homefit.Ui.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.data.ByteArrayFetcher;

import alrefa.android.com.homefit.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerSliderFragment extends Fragment {

    @BindView(R.id.image_bannerslider)
    ImageView imageSlide;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bannerslider_viewpager_template, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public static BannerSliderFragment newInstance(){
        Bundle args = new Bundle();
        BannerSliderFragment bannerSliderFragment = new BannerSliderFragment();
        bannerSliderFragment.setArguments(args);
        return bannerSliderFragment;
    }

    public void setImage(int tag){
//        Toast.makeText(getContext(), String.valueOf(tag), Toast.LENGTH_SHORT).show();
    }
}
