package alrefa.android.com.homefit.Ui.Splash;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class SplashScreenPresenter<V extends SplashScreenMvpView> extends BasePresenter<V>
        implements SplashScreenMvpPresenter {

    @Inject
    public SplashScreenPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
