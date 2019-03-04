package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import alrefa.android.com.homefit.Utils.AppConstants;
import alrefa.android.com.homefit.Utils.AppLogger;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements MainActivityMvpView, ServiceCategoryRecyclerAdapter.CallBack
        , SubServiceCategoryRecyclerAdapter.CallBack, OnMapReadyCallback {

    @Inject
    ServiceCategoryRecyclerAdapter serviceCategoryRecyclerAdapter;

    @BindView(R.id.text_category_indicator)
    TextView textCategoryIndicator;

    @BindView(R.id.recycler_sub_categories)
    RecyclerView recyclerSubCategories;

    @BindView(R.id.text_bannerslider_indicator)
    TextView textBannersliderIndicator;

    @BindView(R.id.text_description_indicator)
    TextView textDescriptionIndicator;

    @BindView(R.id.image_bannerslider)
    ImageView imageBannerslider;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sub_category_indicator_container)
    LinearLayout subCategoryIndicatorContainer;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.recycler_categories)
    RecyclerView recyclerCategories;


    @BindView(R.id.text_sub_category_indicator)
    TextView textSubCategoryIndicator;

    @BindView(R.id.text_description_max_length_indicator)
    TextView textDescriptionMaxLengthIndicator;

    @Inject
    MainActivityPresenter<MainActivityMvpView> mPresenter;

    @Inject
    SubServiceCategoryRecyclerAdapter subServiceCategoryRecyclerAdapter;
    @Inject
    SupportMapFragment supportMapFragment;

    @Inject
    LatLng muscat_latLng;


    private Animation visibilityAnim;

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

        initializeBoldFonts();
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mPresenter.prepareSliders();
        mPresenter.prepareAvailableServices();
        visibilityAnim = AnimationUtils.loadAnimation(this, R.anim.sub_category_gone_anim);
        supportMapFragment.getMapAsync(this);
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

    private void initializeBoldFonts() {

        // TODO: 2/24/19 handle for both english and arabic fonts
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), AppConstants.ENGLISH_BOLD_FONT_PATH);
        textCategoryIndicator.setTypeface(typeface);
        textDescriptionIndicator.setTypeface(typeface);
        textDescriptionMaxLengthIndicator.setTypeface(typeface);
        textBannersliderIndicator.setTypeface(typeface);
        textSubCategoryIndicator.setTypeface(typeface);
    }

    @Override
    public void onSlidersPrepared(List<MainRequests.SliderRequests> sliders) {
        AppLogger.i("sliderImage", sliders.get(0).getImage_url());
        Glide.with(this).load(sliders.get(0)
                .getImage_url()).centerCrop()
                .into(imageBannerslider);
    }

    @Override
    public void onAvailableServiceCategoriesPrepared(List<MainRequests.CategoriesRequests> services) {
        AppLogger.i("onMainActivity", services);
        recyclerCategories.setAnimation(AnimationUtils.loadAnimation(this, R.anim.sub_category_visibility_anim));
        serviceCategoryRecyclerAdapter.addItems(services);
        recyclerCategories.setLayoutManager(linearLayoutManager);
        recyclerCategories.setAdapter(serviceCategoryRecyclerAdapter);

    }

    @Override
    public void onCategoryItemClickSwitch(int selected_position,
                                          int last_selected_position,
                                          MainRequests.CategoriesRequests last_selected_categories,
                                          MainRequests.CategoriesRequests categories,
                                          Context context) {

        if (last_selected_position != -1)
            Glide.with(context).load(last_selected_categories.getImage_url())
                    .into((ImageView) recyclerCategories.getChildAt(last_selected_position)
                            .findViewById(R.id.image_category));

        if (last_selected_categories != null)
            recyclerCategories.getChildAt(last_selected_position)
                    .findViewById(R.id.selected_image).setVisibility(View.GONE);


        Glide.with(context).load(categories.getImage_url())
                .into((ImageView) recyclerCategories.getChildAt(selected_position)
                        .findViewById(R.id.image_category));


        recyclerCategories.getChildAt(selected_position)
                .findViewById(R.id.selected_image).setVisibility(View.VISIBLE);
    }

    @Override
    public void onAvailableServicesPrepared(List<MainRequests.Services> services) {
        subServiceCategoryRecyclerAdapter.clear();
        if (subCategoryIndicatorContainer.getVisibility() == View.GONE) {

            subCategoryIndicatorContainer.setVisibility(View.VISIBLE);
            subCategoryIndicatorContainer
                    .setAnimation(visibilityAnim);
        }
        recyclerSubCategories.setVisibility(View.VISIBLE);
        recyclerSubCategories.setAnimation(AnimationUtils.loadAnimation(this, R.anim.sub_category_visibility_anim));
        subServiceCategoryRecyclerAdapter.addItems(services);
        recyclerSubCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerSubCategories.setAdapter(subServiceCategoryRecyclerAdapter);

    }

    @Override
    public void onNoSubCategoryNeeded() {
        subCategoryIndicatorContainer.setAnimation(AnimationUtils.loadAnimation(this, R.anim.sub_category_gone_anim));
        recyclerSubCategories.setAnimation(AnimationUtils.loadAnimation(this, R.anim.sub_category_gone_anim));
        subCategoryIndicatorContainer.setVisibility(View.GONE);
        recyclerSubCategories.setVisibility(View.GONE);
        subServiceCategoryRecyclerAdapter.clear();
    }


    @Override
    public void onCategoryItemClicked(int selected_position,
                                      int last_selected_position,
                                      MainRequests.CategoriesRequests last_selected_categories,
                                      MainRequests.CategoriesRequests categories,
                                      Context context) {

        mPresenter.switchSelectedCategoryItems(selected_position, last_selected_position,
                last_selected_categories, categories, context);


    }

    @Override
    public void onSubCategoryItemClicked() {


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(muscat_latLng)
                .title("muscat"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(muscat_latLng));
    }
}
