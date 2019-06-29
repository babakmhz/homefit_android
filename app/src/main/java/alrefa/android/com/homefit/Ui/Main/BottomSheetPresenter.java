package alrefa.android.com.homefit.Ui.Main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.providersDataModel;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class BottomSheetPresenter<V extends BottomSheetMvpView> extends BasePresenter<V>
        implements BottomSheetMvpPresenter<V> {


    @Inject
    public BottomSheetPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getDateTime(String service_id) {
        // TODO: 5/6/19 change token
//        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getDateServiceDateTime(BuildConfig.API_KEY
                        , service_id)
                .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui()).subscribe(new Consumer<List<DateTimeDataModel>>() {
                    @Override
                    public void accept(List<DateTimeDataModel> models) throws Exception {
                        List<DateTimeDataModel.Date> dates = new ArrayList<>();
                        for (DateTimeDataModel model :
                                models) {
                            dates.add(model.getDate());
                        }
                        getMvpView().onAvailableDateTimesFetched(dates);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.d("ERROR!!!:", throwable.toString());
                    }
                }));
    }

    @Override
    public void getProviders(MainActivityMvpView mainMvpView) {
        String categoryId = mainMvpView.getSelectedServiceIdFromActivity();
        List<String> serviceIds = mainMvpView.getSelectedServiceIds();
        if (categoryId != null && !"".equals(categoryId) && serviceIds != null && serviceIds.size() > 0) {
            getCompositeDisposable().add(getDataManager()
                    .getAvailableProviders(BuildConfig.API_KEY,
                           categoryId,
                            serviceIds).subscribeOn(getSchedulerProvider().newThread())
            .observeOn(getSchedulerProvider().ui()).subscribe(new Consumer<List<providersDataModel>>() {
                        @Override
                        public void accept(List<providersDataModel> providersDataModels) throws Exception {
                            AppLogger.d("models:",providersDataModels);
                            getMvpView().onProvidersPrepared(providersDataModels);
                        }
                    }));
        }

    }


}
