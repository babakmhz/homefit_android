package alrefa.android.com.homefit.Ui.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.Service;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
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
                getSlidersFromDb()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Slider>>() {
                    @Override
                    public void accept(List<Slider> sliderRequests) throws Exception {
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
    public void prepareSlidersFromServer() {

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
        getCompositeDisposable().add(getDataManager().getCategoriesFromDb()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categoryRequests) throws Exception {
                        AppLogger.i("services", categoryRequests);
                        getMvpView().onAvailableServiceCategoriesPrepared(categoryRequests);
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
    public void prepareAvailableServicesFromServer() {
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

                        getMvpView().onAvailableServiceCategoriesPrepared(categoryRequests);
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
    public void checkPermissions() {

        // TODO: 7/6/19  
    }

    @SuppressLint("CheckResult")
    @Override
    public void switchSelectedCategoryItems(int selected_position,
                                            int last_selected_position,
                                            Category last_selected_category,
                                            Category category,
                                            Context context) {

        if (selected_position != last_selected_position) {
            getMvpView().onCategoryItemClickSwitch(selected_position
                    , last_selected_position
                    , last_selected_category, category, context);

            if (category.getServices() != null && category.getServices().size() > 0) {
                getDataManager().getSubCategoriesFromDb(category.getId()).subscribe(new Consumer<List<Service>>() {
                    @Override
                    public void accept(List<Service> services) throws Exception {
                        getMvpView().onAvailableServicesPrepared(
                                services);
                    }
                });

            } else
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
    public void requestLocationUpdates(final Context context, LocationListener listener) {


        if (!AppUtils.checkLocationPermission(context)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AppUtils.askLocationPermission(getMvpView().getActivity());
            }
            return;
        }
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
                getMvpView().onProviderEnabled(s);
            }

            @Override
            public void onProviderDisabled(String s) {
                // TODO: 3/8/19 handle this
                getMvpView().onProviderDisabled(s);

            }
        }, context);
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
