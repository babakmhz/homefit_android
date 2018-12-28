package alrefa.android.com.homefit.Data;

import android.content.Context;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.DB.DatabaseHelper;
import alrefa.android.com.homefit.Data.Network.ApiHelper;
import alrefa.android.com.homefit.Data.Prefs.PrefsHelper;


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
}
