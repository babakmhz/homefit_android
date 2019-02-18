package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SliderPagerAdapter extends PagerAdapter {


    private final Context context;
    private final LayoutInflater layoutInflater;
    private List<String> imageUrls;
    @BindView(R.id.image_bannerslider)
    ImageView imageSlider;
    private Unbinder unbinder;

    @Inject
    public SliderPagerAdapter(@ActivityContext Context context, List<String> imageUrls) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    public void setImages(List<MainRequests.SliderRequests> urls) {
        imageUrls.clear();
        // TODO: 2/18/19 handle arabic images
        for (MainRequests.SliderRequests obj :
                urls) {
            imageUrls.add(obj.getImage_url());
        }
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.bannerslider_viewpager_template, container, false);
        unbinder = ButterKnife.bind(this, itemView);
        Glide.with(context).load(imageUrls.get(position)).into(imageSlider);
        container.addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
