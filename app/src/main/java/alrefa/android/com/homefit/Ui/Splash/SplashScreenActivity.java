package alrefa.android.com.homefit.Ui.Splash;

import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity
        implements SplashScreenMvpView {

    @Inject
    SplashScreenPresenter<SplashScreenMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        mPresenter.prepareSliders();
        mPresenter.prepareAvailableServices();

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void showMessage(View container, int resId) {

    }

    @Override
    public void onServicePrepared() {

    }

    @Override
    public void onServicePreparingFailed() {

    }

    @Override
    public void onSlidersPrepared() {

    }

    @Override
    public void onSlidersPreparingFailed() {

    }
}
