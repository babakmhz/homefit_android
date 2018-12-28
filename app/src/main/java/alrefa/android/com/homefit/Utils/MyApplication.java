package alrefa.android.com.homefit.Utils;

import android.app.Application;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Component.ApplicationComponent;
import alrefa.android.com.homefit.DI.Component.DaggerApplicationComponent;
import alrefa.android.com.homefit.DI.Module.ApplicationModule;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {


    @Inject
    DataManagerHelper mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        CalligraphyConfig.initDefault(mCalligraphyConfig);

    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }


}
