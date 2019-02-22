package alrefa.android.com.homefit.Data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.DB.DatabaseHelper;
import alrefa.android.com.homefit.Data.Network.ApiHelper;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Data.Prefs.PrefsHelper;
import io.reactivex.Single;


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
}
