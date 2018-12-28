package alrefa.android.com.homefit.DI.Module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.DB.AppDatabase;
import alrefa.android.com.homefit.Data.DB.DatabaseHelper;
import alrefa.android.com.homefit.Data.DataManager;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Data.Network.ApiHelper;
import alrefa.android.com.homefit.Data.Network.AppApiService;
import alrefa.android.com.homefit.Data.Prefs.AppPreferences;
import alrefa.android.com.homefit.Data.Prefs.PrefsHelper;
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
    public ApiHelper ProvideApiHelper() {
        return new AppApiService();
    }

    @Provides
    @Singleton
    public DatabaseHelper ProvideDatabaseHelper() {
        return new AppDatabase();
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
                .setDefaultFontPath("fonts/iran_sans_mobile_medium.ttf")
                .build();
    }
}
