package alrefa.android.com.homefit.Data;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.DB.DatabaseHelper;
import alrefa.android.com.homefit.Data.Network.ApiHelper;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.Data.Prefs.PrefsHelper;
import alrefa.android.com.homefit.Utils.AppLogger;
import io.reactivex.Single;

import static android.support.constraint.Constraints.TAG;


public class DataManager implements DataManagerHelper {

    private final Context context;
    private final PrefsHelper prefsHelper;
    private final ApiHelper apiHelper;
    private DatabaseHelper databaseHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DatabaseHelper databaseHelper,
                       PrefsHelper prefsHelper
            , ApiHelper apiHelper) {

        this.context = context;
        this.databaseHelper = databaseHelper;
        this.prefsHelper = prefsHelper;
        this.apiHelper = apiHelper;
    }


    @Override
    public Single<List<MainRequests.SliderRequests>> getBannerSliders(String token) {
        return apiHelper.getBannerSliders(token);

    }

    @Override
    public Single<List<MainRequests.CategoriesRequests>> getAvailableServices(String token) {
        return apiHelper.getAvailableServices(token);
    }

    @Override
    public Single<List<DateTimeDataModel>> getDateServiceDateTime(String token, String service_id) {
        return apiHelper.getDateServiceDateTime(token,service_id);
    }

    @Override
    public Single<List<ProvidersDataModel>> getAvailableProviders(String token, String category_id, List<String> service_ids) {
        Log.i(TAG, "getAvailableProviders: "+service_ids.toString());
        return apiHelper.getAvailableProviders(token,category_id,service_ids);
    }
}
