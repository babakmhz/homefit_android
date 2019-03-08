package alrefa.android.com.homefit.Ui.Main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import alrefa.android.com.homefit.Utils.AppConstants;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.GoogleMapsCustomSupportFragment;
import alrefa.android.com.homefit.Utils.KeyboardUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends BaseActivity
        implements MainActivityMvpView, ServiceCategoryRecyclerAdapter.CallBack
        , SubServiceCategoryRecyclerAdapter.CallBack,
        OnMapReadyCallback, GoogleMap.OnMapClickListener,
        LocationSource.OnLocationChangedListener {

    @Inject
    ServiceCategoryRecyclerAdapter serviceCategoryRecyclerAdapter;

    @BindView(R.id.text_category_indicator)
    TextView textCategoryIndicator;

    @BindView(R.id.recycler_sub_categories)
    RecyclerView recyclerSubCategories;

    @BindView(R.id.exit_text_address_map)
    EditText editTextAddressMap;

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

    @BindView(R.id.nested_scrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.text_sub_category_indicator)
    TextView textSubCategoryIndicator;

    @BindView(R.id.text_description_max_length_indicator)
    TextView textDescriptionMaxLengthIndicator;

    @BindView(R.id.text_location_indicator)
    TextView textLocationIndicator;

    @BindView(R.id.text_current_description_length)
    TextView textCurrentDescriptionLength;

    @Inject
    MainActivityPresenter<MainActivityMvpView> mPresenter;

    @Inject
    SubServiceCategoryRecyclerAdapter subServiceCategoryRecyclerAdapter;
    @Inject
    SupportMapFragment supportMapFragment;

    @Inject
    LatLng muscat_latLng;

    @Inject
    Geocoder geocoder;

    @BindView(R.id.editText_description)
    EditText editTextDescription;

    @BindView(R.id.progress_map)
    ProgressBar progressMap;

    @BindView(R.id.fab_find_me)
    FloatingActionButton findMeFab;


    private Animation visibilityAnim;
    private CameraUpdate cameraUpdate;
    private GoogleMap googleMap;
    private Marker you_marker;
    private Animation map_editText_visibilty_anim;

    private Location current_location;
    private GoogleApiClient googleApiClient;

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppConstants.LOCATION_PERMISSION_REQUEST_CODE && grantResults[0] == 0)
            mPresenter.getLastKnownLocation(getApplicationContext());
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
        mPresenter.getLastKnownLocation(getApplicationContext());
        supportMapFragment = (GoogleMapsCustomSupportFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mPresenter.prepareSliders();
        mPresenter.prepareAvailableServices();
        map_editText_visibilty_anim = AnimationUtils.loadAnimation(this, R.anim.sub_category_visibility_anim);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkLocationPermissionOrUpdateLocation() {
        // TODO: 3/8/19 move this on intro slider
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionsSafely(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}
                    , AppConstants.LOCATION_PERMISSION_REQUEST_CODE);
        } else
            mPresenter.getLastKnownLocation(getApplicationContext());
    }

    private void initializeBoldFonts() {
        // TODO: 2/24/19 handle for both english and arabic fonts
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), AppConstants.ENGLISH_BOLD_FONT_PATH);
        textCategoryIndicator.setTypeface(typeface);
        textDescriptionIndicator.setTypeface(typeface);
        textDescriptionMaxLengthIndicator.setTypeface(typeface);
        textBannersliderIndicator.setTypeface(typeface);
        textSubCategoryIndicator.setTypeface(typeface);
        textLocationIndicator.setTypeface(typeface);
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
    public void onAddressFromLatLngReady(String addresses) {
        if (editTextAddressMap.getVisibility() != View.VISIBLE)
            editTextAddressMap.setVisibility(View.VISIBLE);
        editTextAddressMap.setAnimation(map_editText_visibilty_anim);
        editTextAddressMap.setText(addresses);
        editTextAddressMap.setFocusableInTouchMode(true);
        editTextAddressMap.requestFocus();
        KeyboardUtils.showSoftInput(editTextAddressMap, this);
    }

    @Override
    public void onAddressFromLatLngError() {
        // TODO: 3/7/19 handle That
    }

    @Override
    public void showLoadingOnMap() {
        // TODO: 3/7/19 fix conditions in presenter
        progressMap.setVisibility(View.VISIBLE);
        if (editTextAddressMap.getVisibility() != View.VISIBLE)
            editTextAddressMap.setVisibility(View.VISIBLE);
        editTextAddressMap.setAnimation(map_editText_visibilty_anim);
        editTextAddressMap.setText(R.string.please_wait_while_obtaining_your_address);
    }

    @Override
    public void hideLoadingOnMap() {
        progressMap.setVisibility(View.GONE);
    }

    @Override
    public void onLocationUpdatePrepared(Location location) {
        this.current_location = location;
        if (location != null) {
            onMapClick(new LatLng(location.getLongitude(), location.getLatitude()));
        }
    }

    @Override
    public void onLocationUpdateFailed() {
        // TODO: 3/7/19 handle this
    }

    @Override
    public Location getCurrentLocation() {
        return current_location;
    }

    @Override
    public void onRequestLocationNotPrepared() {
        mPresenter.requestLocationUpdates(this);
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
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        // TODO: 3/4/19 grant permission on app intro slider and handle Location Errors
        final MarkerOptions you_marker_option = new MarkerOptions().position(muscat_latLng).title("you");
        you_marker = googleMap.addMarker(you_marker_option);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        ((GoogleMapsCustomSupportFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .setListener(new GoogleMapsCustomSupportFragment.OnTouchListener() {
                    @Override
                    public void onTouch() {
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                });
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(muscat_latLng, AppConstants.GOOGLE_MAPS_CAMERA_ZOOM);
        googleMap.moveCamera(cameraUpdate);
        googleMap.animateCamera(cameraUpdate);
        googleMap.setOnMapClickListener(this);


    }

    @OnTextChanged(R.id.editText_description)
    public void onDescriptionTextChanged(CharSequence charSequence) {
        textCurrentDescriptionLength.setText(String.valueOf(charSequence.toString().length()));
    }

    @Override
    public void onMapClick(final LatLng latLng) {
        if (you_marker != null) {
            you_marker.setPosition(latLng);
            cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
            googleMap.animateCamera(cameraUpdate);
            mPresenter.getAddress(latLng, geocoder);
        }

    }

    @OnClick(R.id.fab_find_me)
    public void onFabClick() {
        mPresenter.requestLocationUpdates(getApplicationContext());
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null)
            onMapClick(new LatLng(location.getLatitude(), location.getLongitude()));
    }


}
