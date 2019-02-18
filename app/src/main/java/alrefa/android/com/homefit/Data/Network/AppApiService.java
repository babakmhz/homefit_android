package alrefa.android.com.homefit.Data.Network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.ApiHeader;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Utils.ApiEndpoints;
import io.reactivex.Single;

public class AppApiService implements ApiHelper {

    // TODO: 2/17/19 todo api token and start developing registration process

    public static final String header = "84c2907aaed14d11b1c2411c5cba60da";

    private ApiHeader apiHeader;
//
//    @Inject
//    public AppApiService(ApiHeader apiHeader) {
//        this.apiHeader = apiHeader;
//    }

    @Override
    public Single<List<MainRequests.SliderRequests>> getBannerSliders(String token) {

        return Rx2AndroidNetworking.get(ApiEndpoints.SLIDER_ENDPOINT)
                .addHeaders("AUTH", token).
                        build().
                        getObjectListSingle(MainRequests.SliderRequests.class);
    }
}
