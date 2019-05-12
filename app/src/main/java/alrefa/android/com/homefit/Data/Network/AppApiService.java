package alrefa.android.com.homefit.Data.Network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Utils.ApiEndpoints;
import io.reactivex.Single;

public class AppApiService implements ApiHelper {

    // TODO: 2/17/19 todo api token and start developing registration process


    @Override
    public Single<List<MainRequests.SliderRequests>> getBannerSliders(String token) {

        return Rx2AndroidNetworking.get(ApiEndpoints.SLIDER_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token).
                        build().
                        getObjectListSingle(MainRequests.SliderRequests.class);
    }

    @Override
    public Single<List<MainRequests.CategoriesRequests>> getAvailableServices(String token) {
        return Rx2AndroidNetworking.get(ApiEndpoints.CATEGORIES_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .build().getObjectListSingle(MainRequests.CategoriesRequests.class);
    }

    @Override
    public Single<List<DateTimeDataModel>> getDateServiceDateTime(String token, String service_id) {
        return Rx2AndroidNetworking.get(ApiEndpoints.SERVICE_DATETIMIE_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .addQueryParameter("id", service_id)
                .build().getObjectListSingle(DateTimeDataModel.class);
    }
}
