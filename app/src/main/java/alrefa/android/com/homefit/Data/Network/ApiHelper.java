package alrefa.android.com.homefit.Data.Network;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.OrderDataModel;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
import io.reactivex.Single;

public interface ApiHelper {

    Single<List<Slider>> getBannerSliders(String token);

    Single<List<Category>> getAvailableServices(String token);

    Single<List<DateTimeDataModel>> getDateServiceDateTime(String token, String service_id);

    Single<List<ProvidersDataModel>> getAvailableProviders(String token, String category_id, List<String> service_ids);

    Single<OrderDataModel.Response> submitOrder(String token, OrderDataModel orderDataModel);
}
