package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.location.Location;
import android.view.View;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Ui.Base.MvpView;

public interface MainActivityMvpView extends MvpView {

    void onSlidersPrepared(List<MainRequests.SliderRequests> sliders);

    void onAvailableServiceCategoriesPrepared(List<MainRequests.CategoriesRequests> services);

    void onCategoryItemClickSwitch(int selected_position,
                                   int last_selected_position,
                                   MainRequests.CategoriesRequests last_selected_categories,
                                   MainRequests.CategoriesRequests categories,
                                   Context context);

    void onAvailableServicesPrepared(List<MainRequests.Services> services);

    void onNoSubCategoryNeeded();

    void onAddressFromLatLngReady(String addresses);

    void onAddressFromLatLngError();

    void showLoadingOnMap();

    void hideLoadingOnMap();

    void onLocationUpdatePrepared(Location location);

    void onLocationUpdateFailed();

    Location getCurrentLocation();

    void onRequestLocationNotPrepared();

    List<MainRequests.Services> getSelectedServices();

    String getDescription();

    String getAddress();

    String getLocationInLatLng();

    View getSnackbarView();

    Context getContext();

    void proceedToOrderActivity();


}
