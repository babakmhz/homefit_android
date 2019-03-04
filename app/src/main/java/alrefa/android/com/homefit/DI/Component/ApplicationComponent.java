package alrefa.android.com.homefit.DI.Component;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import javax.inject.Singleton;

import alrefa.android.com.homefit.DI.Module.ApplicationModule;
import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.DI.Qualifier.Bold_en;
import alrefa.android.com.homefit.Data.DataManagerHelper;
import alrefa.android.com.homefit.Utils.MyApplication;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MyApplication myApp);

    Application getMyapp();


    DataManagerHelper getDataManagerHelper();

    @ApplicationContext
    Context getContext();


}
