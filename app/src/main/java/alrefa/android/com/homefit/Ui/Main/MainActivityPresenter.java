package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivityPresenter<V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter {

    @Inject
    public MainActivityPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void prepareSliders() {

        // TODO: 2/17/19 remove buildConfig.API_key if buildConfig == debug else....

        getCompositeDisposable().add(getDataManager().
                getBannerSliders(BuildConfig.API_KEY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<MainRequests.SliderRequests>>() {
                    @Override
                    public void accept(List<MainRequests.SliderRequests> sliderRequests) throws Exception {
                        AppLogger.e("slider", sliderRequests);
                        getMvpView().onSlidersPrepared(sliderRequests);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.i("sliderError", throwable.toString());
                    }
                }));


    }

    @Override
    public void prepareAvailableServices() {
        // TODO: 2/22/19 add view.showLoading & hideLoading
        getCompositeDisposable().add(getDataManager().getAvailableServices(BuildConfig.API_KEY)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<MainRequests.CategoriesRequests>>() {
                    @Override
                    public void accept(List<MainRequests.CategoriesRequests> categoriesRequests) throws Exception {
                        AppLogger.i("services", categoriesRequests);
                        getMvpView().onAvailableServiceCategoriesPrepared(categoriesRequests);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.i("servicesError", throwable.toString());
                        // TODO: 2/23/19 handle Errors
                    }
                }));
    }

    @Override
    public void switchSelectedCategoryItems(int selected_position,
                                            int last_selected_position,
                                            MainRequests.CategoriesRequests last_selected_categories,
                                            MainRequests.CategoriesRequests categories,
                                            Context context) {

        if (selected_position != last_selected_position) {
            getMvpView().onCategoryItemClickSwitch(selected_position
                    , last_selected_position
                    , last_selected_categories, categories, context);

            if (categories.getServices() != null && categories.getServices().size() > 0)
                getMvpView().onAvailableServicesPrepared(categories.getServices());
            else
                getMvpView().onNoSubCategoryNeeded();
        }
    }


}
