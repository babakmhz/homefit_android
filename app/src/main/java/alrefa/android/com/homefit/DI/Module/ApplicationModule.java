package alrefa.android.com.homefit.DI.Module;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Singleton;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.DB.AppDatabase;
import alrefa.android.com.homefit.Data.DB.DatabaseHelper;
import alrefa.android.com.homefit.Data.DB.DbOpenHelper;
import alrefa.android.com.homefit.Data.DataManager;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.ApiHelper;
import alrefa.android.com.homefit.Data.Network.AppApiService;
import alrefa.android.com.homefit.Data.Network.Model.ApiHeader;
import alrefa.android.com.homefit.Data.Network.Model.DaoMaster;
import alrefa.android.com.homefit.Data.Network.Model.DaoSession;
import alrefa.android.com.homefit.Data.Prefs.AppPreferences;
import alrefa.android.com.homefit.Data.Prefs.PrefsHelper;
import alrefa.android.com.homefit.Utils.rx.AppSchedulerProvider;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {


    private Application myApp;

    @Singleton
    public ApplicationModule(Application myApp) {

        this.myApp = myApp;

    }

    @Provides
    @Singleton
    public Application ProvideApplication() {
        return myApp;
    }


    @Provides
    @Singleton
    @ApplicationContext
    public Context ProvideApplicationContext() {
        return myApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public PrefsHelper ProvidePrefsHelper() {
        return new AppPreferences();
    }

    @Provides
    @Singleton
    public ApiHelper ProvideApiHelper(ApiHeader apiHeader) {
        return new AppApiService();
    }


    @Provides
    @Singleton
    public DataManagerHelper ProvideDataManagerHelper(
            @ApplicationContext Context context,
            DatabaseHelper databaseHelper,
            PrefsHelper prefsHelper,
            ApiHelper apiHelper) {
        return new DataManager(context, databaseHelper,
                prefsHelper, apiHelper);

    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/en/Lato-Medium.ttf")
                .build();
    }

    @Provides
    @Singleton
    public ApiHeader.PublicApiHeader ProvidePublicApiHeader() {
        return new ApiHeader.PublicApiHeader();
    }

    @Provides
    @Singleton
    public ApiHeader ProvideApiHeader(ApiHeader.PublicApiHeader apiHeader) {
        return new ApiHeader(apiHeader);


    }


    @Provides
    @Singleton
    public DaoSession ProvideWritableDaoSession(@ApplicationContext Context context,
                                                DbOpenHelper openHelper) {
        return new DaoMaster(openHelper.getWritableDb()).newSession();
    }

    @Provides
    @Singleton
    public DbOpenHelper ProvideDbOpenHelper(@ApplicationContext Context context) {
        return new DbOpenHelper(context);
    }


    @Provides
    @Singleton
    public SchedulerProvider ProvideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    public DatabaseHelper ProvideDbHelper(DbOpenHelper helper, SchedulerProvider provider) {
        return new AppDatabase(helper, provider);
    }


    @Provides
    @Singleton
    public AppApiService ProvideAppAPiService(ApiHeader apiHeader) {
        return new AppApiService();
    }


    @Provides
    public LatLng ProvidesOmanLatLng() {
        return new LatLng(23.614328, 58.545284);
    }

}
