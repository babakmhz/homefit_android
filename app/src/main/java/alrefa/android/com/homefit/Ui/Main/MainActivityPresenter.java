package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Data.Network.Model.OrderDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BasePresenter;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.AppUtils;
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

    @Override
    public void getAddress(LatLng latLng, Geocoder geocoder) {
        // getMvpView().showLoadingOnMap();
        try {
            getCompositeDisposable().add(AppUtils.getAddress(latLng, geocoder)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) {
                            AppLogger.i("address", s);
                            Log.i("address", "accept: " + s);
                            //getMvpView().hideLoadingOnMap();
                            getMvpView().onAddressFromLatLngReady(s);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestLocationUpdates(Context context) {
        getMvpView().showLoadingOnMap();
        AppUtils.updateLocation(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                getMvpView().hideLoadingOnMap();
                getMvpView().onLocationUpdatePrepared(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                // TODO: 3/8/19 handle this
            }

            @Override
            public void onProviderDisabled(String s) {
                // TODO: 3/8/19 handle this
            }
        }, context);
    }

    @Override
    public void getUpdatedLocation() {
        // TODO: 3/8/19 show loading
        getMvpView().showLoadingOnMap();
        if (getMvpView().getCurrentLocation() != null) {
            getMvpView().onLocationUpdatePrepared(getMvpView().getCurrentLocation());
        } else {
            getMvpView().onRequestLocationNotPrepared();
        }
        getMvpView().hideLoadingOnMap();
    }

    @Override
    public void getLastKnownLocation(Context context) {
        if (AppUtils.getLastKnownLocation(context) != null)
            getMvpView().onLocationUpdatePrepared(AppUtils.getLastKnownLocation(context));
    }

    @Override
    public void onSubmitButtonClicked() {

        // TODO: 5/10/19 validate this
        if (getMvpView().getSelectedServices() == null ||
                getMvpView().getSelectedServices().size() <= 0) {
            getMvpView().showMessage(getMvpView().getSnackbarView(),
                    getMvpView().getContext().getString(R.string.please_choose_your_services));
            return;
        }

        if ((getMvpView().getLocationInLatLng() == null || "".equals(getMvpView().getLocationInLatLng()))
                && (getMvpView().getAddress() == null || "".equals(getMvpView().getAddress()))) {
            getMvpView().showMessage(getMvpView().getSnackbarView(), getMvpView().getContext().getString(R.string.please_enter_your_address_or_choose_by_tapping_on_map));
            return;
        }

        getMvpView().showBottomSheetView();

    }




}
