package alrefa.android.com.homefit.Ui.Splash;

import android.annotation.SuppressLint;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.NetworkUtils;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SplashScreenPresenter<V extends SplashScreenMvpView> extends BasePresenter<V>
        implements SplashScreenMvpPresenter {

    @Inject
    public SplashScreenPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void prepareAvailableServices() {
        if (!NetworkUtils.isNetworkConnected(getMvpView().getContext())) {
            getMvpView().onInternetConnectionFailed();
            return;
        }
        // TODO: 2/22/19 add view.showLoading & hideLoading
        getCompositeDisposable().add(getDataManager().getAvailableServices(BuildConfig.API_KEY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Category>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(List<Category> categoryRequests) throws Exception {
                        AppLogger.i("services", categoryRequests);
//                        getMvpView().onAvailableServiceCategoriesPrepared(categoryRequests);
                        for (Category category :
                                categoryRequests) {
                            getDataManager().insertCategories(category).subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {

                                }
                            });
                        }

                        getMvpView().onServicePrepared();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.i("servicesError", throwable.toString());
                        // TODO: 2/23/19 handle Errors
                        getMvpView().onServicePreparingFailed();
                    }
                }));
    }

    @Override
    public void prepareSliders() {
        // TODO: 2/17/19 remove buildConfig.API_key if buildConfig == debug else....
        if (!NetworkUtils.isNetworkConnected(getMvpView().getContext())) {
            getMvpView().onInternetConnectionFailed();
            return;
        }
        getCompositeDisposable().add(getDataManager().
                getBannerSliders(BuildConfig.API_KEY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Slider>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(List<Slider> sliderRequests) throws Exception {
                        AppLogger.e("slider", sliderRequests);
                        for (Slider slider :
                                sliderRequests) {
                            getDataManager().insertSliders(slider).subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    AppLogger.d("sliderInsertResult", aLong);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
                        }

                        getMvpView().onSlidersPrepared();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.i("sliderError", throwable.toString());
                        getMvpView().onSlidersPreparingFailed();
                    }
                }));


    }


    @Override
    public void saveCategories() {

    }
}
