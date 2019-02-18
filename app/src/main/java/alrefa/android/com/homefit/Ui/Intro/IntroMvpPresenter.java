package alrefa.android.com.homefit.Ui.Intro;

import alrefa.android.com.homefit.Ui.Base.MvpPresenter;

public interface IntroMvpPresenter<V extends IntroMvpView> extends MvpPresenter<V> {

    void onPagerScrolled(int lastPosition, int currentPosition);
}
