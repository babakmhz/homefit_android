package alrefa.android.com.homefit.Ui.Main;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.OrderDataModel;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class BottomSheetPresenter<V extends BottomSheetMvpView> extends BasePresenter<V>
        implements BottomSheetMvpPresenter<V> {

    private static final String TAG = "BottomSheetPresenter";

    @Inject
    public BottomSheetPresenter(DataManagerHelper dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getDateTime(String service_id) {
        // TODO: 5/6/19 change token
//        getMvpView().showLoading();
        getMvpView().showDateTimeProgress();
        getCompositeDisposable().add(getDataManager()
                .getDateServiceDateTime(BuildConfig.API_KEY
                        , service_id)
                .subscribeOn(getSchedulerProvider().newThread()).observeOn(getSchedulerProvider().ui()).subscribe(new Consumer<List<DateTimeDataModel>>() {
                    @Override
                    public void accept(List<DateTimeDataModel> models) throws Exception {
                        AppLogger.d("models : %s", models.toString());
                        Log.i(TAG, "acceptRESULT: " + models.toString());

                        List<DateTimeDataModel.Date> dates = new ArrayList<>();
                        // TODO: 7/1/19 fix here
                        for (DateTimeDataModel.Dates model :
                                models.get(0).getDates()) {
                            dates.add(model.getDate());
                        }


                        // TODO: 7/1/19 fix here
                        List<DateTimeDataModel.Time> times = new ArrayList<>();
                        for (DateTimeDataModel.Dates model :
                                models.get(0).getDates()) {
                            times.add(model.getTime());
                        }

                        getMvpView().onAvailableDatesFetched(dates);
                        getMvpView().onAvailableTimesFetched(times);
                        getMvpView().hideDateTimeProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "acceptERROR: " + throwable.toString());
                        AppLogger.e("ERROR!!!:", throwable.toString());
                    }
                }));
    }

    @Override
    public void getProviders(final MainActivityMvpView mainMvpView) {
        // TODO: 7/1/19 change token
        String categoryId = mainMvpView.getSelectedServiceIdFromActivity();
        List<String> serviceIds = mainMvpView.getSelectedServiceIds();
        if (categoryId != null && !"".equals(categoryId) && serviceIds != null && serviceIds.size() > 0) {
            try {
                getCompositeDisposable().add(getDataManager()
                        .getAvailableProviders(BuildConfig.API_KEY,
                                categoryId,
                                serviceIds).subscribeOn(getSchedulerProvider().newThread())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<ProvidersDataModel>>() {
                            @Override
                            public void accept(List<ProvidersDataModel> ProvidersDataModels) throws Exception {
                                AppLogger.i("models:", ProvidersDataModels);
                                getMvpView().onProvidersPrepared(ProvidersDataModels);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mainMvpView.onSubmitOrderFailed();

                            }
                        }));
            } catch (Exception e) {
                e.printStackTrace();
                mainMvpView.onSubmitOrderFailed();
            }
        }

    }

    @Override
    public void submitOrder(final MainActivityMvpView mainActivityMvpView) {

        // TODO: 7/4/19 check if user didn't select one of items animate and change color of that item to red.
        if (mainActivityMvpView.getSelectedProvider() == null) {
            getMvpView().showMessage(getMvpView().getRootView(), R.string.Please_Choose_Provider_To_Continue);
            return;
        }

        if (mainActivityMvpView.getSelectedDate() == null) {
            getMvpView().showMessage(getMvpView().getRootView(), R.string.Please_Choose_Date_To_Continue);
            return;
        }

        if (mainActivityMvpView.getSelectedTime() == null) {
            getMvpView().showMessage(getMvpView().getRootView(), R.string.Please_Choose_Time_To_Continue);
            Toast.makeText(mainActivityMvpView.getContext(), "time failed", Toast.LENGTH_SHORT).show();
            return;
        }
        OrderDataModel model = new OrderDataModel();
        model.setProvider(Integer.parseInt(mainActivityMvpView.getSelectedProvider()));
        model.setDateTime(mainActivityMvpView.getSelectedDate() + " -- " + mainActivityMvpView.getSelectedTime());
        model.setDescription(mainActivityMvpView.getDescription());
        model.setLocation(mainActivityMvpView.getUserLocation());
        model.setTotal_cost(mainActivityMvpView.getOrderTotalCost());
        model.setServices(mainActivityMvpView.getSelectedServiceIds().toString());
        // TODO: 7/4/19 fix token
        mainActivityMvpView.showLoading();
        getCompositeDisposable().add(getDataManager().submitOrder(BuildConfig.API_KEY
                , model).observeOn(getSchedulerProvider().ui()).subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<OrderDataModel.Response>() {
                    @Override
                    public void accept(OrderDataModel.Response response) throws Exception {
                        mainActivityMvpView.hideLoading();
                        AppLogger.d("result:", response);
                        mainActivityMvpView.onSubmitOrderSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.d("resultE:", throwable.toString());
                        mainActivityMvpView.hideLoading();
                        mainActivityMvpView.onSubmitOrderFailed();
                    }
                }));
    }


}
