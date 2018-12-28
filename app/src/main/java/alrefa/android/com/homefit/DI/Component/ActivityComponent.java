package alrefa.android.com.homefit.DI.Component;

import alrefa.android.com.homefit.DI.Module.ActivityModule;
import alrefa.android.com.homefit.DI.Scope.PerActivity;
import alrefa.android.com.homefit.Ui.MainActivity.MainActivity;
import dagger.Component;

@PerActivity
@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
