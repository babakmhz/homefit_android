package alrefa.android.com.homefit.Ui.Splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.Prefs.AppPreferences;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import alrefa.android.com.homefit.Ui.Intro.IntroActivity;
import alrefa.android.com.homefit.Ui.Main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashScreenActivity extends BaseActivity
        implements SplashScreenMvpView {

    List<Integer> results = new ArrayList<>(); //first index is sliders, second index is services

    boolean is_showing_error = false;

    @BindView(R.id.loading_container)
    LinearLayout loading_container;

    @BindView(R.id.no_internet_container)
    LinearLayout noInternet_container;

    @Inject
    SplashScreenPresenter<SplashScreenMvpView> mPresenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppPreferences appPreferences = new AppPreferences(this);
        setContentView(R.layout.activity_spash_screen);
        if (!appPreferences.get_firstLogin()) {
            startActivity(new Intent(this
                    , IntroActivity.class));
            finish();
        } else {


            getActivityComponent().inject(this);

            setUnBinder(ButterKnife.bind(this));

            mPresenter.onAttach(this);


            results.add(0);
            results.add(0);

            mPresenter.prepareSliders();

            mPresenter.prepareAvailableServices();

        }


    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null)
        mPresenter.onDetach();
    }

    @Override
    public void showMessage(View container, int resId) {

    }

    @Override
    public void onServicePrepared() {
        results.set(1, 1);
        if (results.get(0) == 1) {
            startMainActivity();
            finish();
        }
    }

    @Override
    public void onServicePreparingFailed() {

        if (!is_showing_error) {
            is_showing_error = true;
            loading_container.setVisibility(View.GONE);
            noInternet_container.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSlidersPrepared() {
        results.set(0, 1);
        if (results.get(1) == 1) {
            startMainActivity();
            finish();

        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSlidersPreparingFailed() {

        if (!is_showing_error) {
            is_showing_error = true;
            loading_container.setVisibility(View.GONE);
            noInternet_container.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onInternetConnectionFailed() {
        if (!is_showing_error) {
            is_showing_error = true;
            loading_container.setVisibility(View.GONE);
            noInternet_container.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showLoadingServices() {
        if (is_showing_error) {
            is_showing_error = false;
            loading_container.setVisibility(View.VISIBLE);
            noInternet_container.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.bt_tryAgain)
    public void onBtTryAgainClicked() {
        showLoadingServices();
        mPresenter.prepareSliders();
        mPresenter.prepareAvailableServices();
    }
}
