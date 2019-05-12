package alrefa.android.com.homefit.Data.Network;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import io.reactivex.Single;

public interface ApiHelper {

    Single<List<MainRequests.SliderRequests>> getBannerSliders(String token);

    Single<List<MainRequests.CategoriesRequests>> getAvailableServices(String token);

    Single<List<DateTimeDataModel>> getDateServiceDateTime(String token, String service_id);
}
