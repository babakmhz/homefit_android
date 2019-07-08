package alrefa.android.com.homefit.Ui.Splash;

import alrefa.android.com.homefit.Ui.Base.MvpView;

public interface SplashScreenMvpView extends MvpView {

    void onServicePrepared();
    void onServicePreparingFailed();
    void onSlidersPrepared();
    void onSlidersPreparingFailed();
}
