package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;

public interface MainActivityMvpPresenter {

    void prepareSliders();

    void prepareAvailableServices();
/*

    void switchSelectedCategoryItems(int selected_position,
                                     int last_selected_position,
                                     List<MainRequests.Services> services);
*/


    void switchSelectedCategoryItems(int selected_position,
                                     int last_selected_position,
                                     MainRequests.CategoriesRequests last_selected_categories,
                                     MainRequests.CategoriesRequests categories,
                                     Context context);

    void getAddress(LatLng latLng, Geocoder geocoder);

    void requestLocationUpdates(Context context);

    void getUpdatedLocation();

    void getLastKnownLocation(Context context);


}
