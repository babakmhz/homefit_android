package alrefa.android.com.homefit.Ui.Splash;

import android.content.Context;

import alrefa.android.com.homefit.Ui.Base.MvpView;

public interface SplashScreenMvpView extends MvpView {

    void onServicePrepared();
    void onServicePreparingFailed();
    void onSlidersPrepared();
    void onSlidersPreparingFailed();
    Context getContext();
    void onInternetConnectionFailed();
    void showLoadingServices();
}
