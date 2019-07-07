package alrefa.android.com.homefit.Data.Network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.OrderDataModel;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
import alrefa.android.com.homefit.Utils.ApiEndpoints;
import alrefa.android.com.homefit.Utils.AppLogger;
import io.reactivex.Single;

public class AppApiService implements ApiHelper {

    // TODO: 2/17/19 todo api token and start developing registration process


    @Override
    public Single<List<Slider>> getBannerSliders(String token) {

        return Rx2AndroidNetworking.get(ApiEndpoints.SLIDER_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token).
                        build().
                        getObjectListSingle(Slider.class);
    }

    @Override
    public Single<List<Category>> getAvailableServices(String token) {
        return Rx2AndroidNetworking.get(ApiEndpoints.CATEGORIES_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .build().getObjectListSingle(Category.class);
    }

    @Override
    public Single<List<DateTimeDataModel>> getDateServiceDateTime(String token, String service_id) {
        return Rx2AndroidNetworking.get(ApiEndpoints.SERVICE_DATETIMIE_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .addQueryParameter("id", service_id)
                .build().getObjectListSingle(DateTimeDataModel.class);
    }

    @Override
    public Single<List<ProvidersDataModel>> getAvailableProviders(String token, String category_id, List<String> service_ids) {
        AppLogger.d("serviceIDS:!!! ", service_ids);
        return Rx2AndroidNetworking.get(ApiEndpoints.AVAILABLE_PROVIDERS_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .addQueryParameter("category", category_id)
                .addQueryParameter("services", service_ids.toString())
                .build().getObjectListSingle(ProvidersDataModel.class);
    }

    @Override
    public Single<OrderDataModel.Response> submitOrder(String token, OrderDataModel orderDataModel) {
        return Rx2AndroidNetworking.post(ApiEndpoints.SUBMIT_ORDER_ENDPOINT)
                .addHeaders(ApiEndpoints.HEADER_AUTH_KEY, token)
                .addBodyParameter(orderDataModel)
                .build()
                .getObjectSingle(OrderDataModel.Response.class);
    }
}
