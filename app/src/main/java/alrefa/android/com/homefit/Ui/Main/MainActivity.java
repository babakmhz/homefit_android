package alrefa.android.com.homefit.Ui.Main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import alrefa.android.com.homefit.Utils.AppLogger;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements MainActivityMvpView, ServiceCategoryRecyclerAdapter.CallBack {


    @Inject
    ServiceCategoryRecyclerAdapter serviceCategoryRecyclerAdapter;

    @BindView(R.id.image_bannerslider)
    ImageView imageBannerslider;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    MainActivityPresenter<MainActivityMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mPresenter.prepareSliders();
        mPresenter.prepareAvailableServices();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSlidersPrepared(List<MainRequests.SliderRequests> sliders) {
        AppLogger.i("sliderImage",sliders.get(0).getImage_url());
        Glide.with(this).load(sliders.get(0)
                .getImage_url()).centerCrop()
                .into(imageBannerslider);
    }

    @Override
    public void onAvailableServicesPrepared(List<MainRequests.CategoriesRequests> sliders) {

    }
}
