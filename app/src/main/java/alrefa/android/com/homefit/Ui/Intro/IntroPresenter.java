package alrefa.android.com.homefit.Ui.Intro;

import javax.inject.Inject;

import alrefa.android.com.homefit.Data.DataManager;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class IntroPresenter<V extends IntroMvpView> extends
        BasePresenter<V> implements IntroMvpPresenter<V> {

    @Inject
    public IntroPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onPagerScrolled(int lastPosition, int currentPosition) {

    }
}
