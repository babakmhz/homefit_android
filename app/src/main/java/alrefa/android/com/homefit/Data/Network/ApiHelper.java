package alrefa.android.com.homefit.Data.Network;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import io.reactivex.Single;

public interface ApiHelper {

    Single<List<MainRequests.SliderRequests>> getBannerSliders(String token);
}
