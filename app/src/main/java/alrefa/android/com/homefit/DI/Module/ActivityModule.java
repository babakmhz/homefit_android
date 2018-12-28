package alrefa.android.com.homefit.DI.Module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Utils.rx.AppSchedulerProvider;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {


    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    public Context ProvideMainActivityContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    public SchedulerProvider ProvideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


}
