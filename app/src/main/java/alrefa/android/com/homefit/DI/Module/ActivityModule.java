package alrefa.android.com.homefit.DI.Module;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.animation.Animation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.DI.Qualifier.Bold_en;
import alrefa.android.com.homefit.DI.Scope.PerActivity;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Ui.Intro.FragmentSlide1;
import alrefa.android.com.homefit.Ui.Intro.FragmentSlide2;
import alrefa.android.com.homefit.Ui.Intro.FragmentSlide3;
import alrefa.android.com.homefit.Ui.Intro.FragmentSlide4;
import alrefa.android.com.homefit.Ui.Intro.IntroMvpPresenter;
import alrefa.android.com.homefit.Ui.Intro.IntroMvpView;
import alrefa.android.com.homefit.Ui.Intro.IntroPresenter;
import alrefa.android.com.homefit.Ui.Intro.IntroSliderViewPagerAdapter;
import alrefa.android.com.homefit.Ui.Main.MainActivity;
import alrefa.android.com.homefit.Ui.Main.ServiceCategoryRecyclerAdapter;
import alrefa.android.com.homefit.Ui.Main.SubServiceCategoryRecyclerAdapter;
import alrefa.android.com.homefit.Utils.MyApplication;
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


    @Provides
    @PerActivity
    IntroMvpPresenter<IntroMvpView> ProvideIntroMvpPresenter(IntroPresenter<IntroMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IntroSliderViewPagerAdapter ProvideSliderViewPagerAdapter() {
        return new IntroSliderViewPagerAdapter(activity.getSupportFragmentManager());
    }


    @Provides
    @PerActivity
    List<Fragment> ProvideSliderFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentSlide1());
        fragments.add(new FragmentSlide2());
        fragments.add(new FragmentSlide3());
        fragments.add(new FragmentSlide4());
        return fragments;
    }

    @Provides
    @PerActivity
    public List<MainRequests.CategoriesRequests> ProvideServiceCategoryList() {
        return new ArrayList<>();
    }

    @Provides
    @PerActivity
    public List<MainRequests.Services> ProvideSubServiceCategoryList() {
        return new ArrayList<>();
    }

    @Provides
    @PerActivity
    public ServiceCategoryRecyclerAdapter.CallBack ProvideCategoryRecyclerCallback(){
        return (MainActivity) activity;
    }

    @Provides
    @PerActivity
    public ServiceCategoryRecyclerAdapter ProvideServiceCategoryRecyclerAdapter
            (@ActivityContext Context context, List<MainRequests.CategoriesRequests> data
            , ServiceCategoryRecyclerAdapter.CallBack callBack) {
        return new ServiceCategoryRecyclerAdapter(context, data,callBack);
    }

    @Provides
    @PerActivity
    public SubServiceCategoryRecyclerAdapter.CallBack ProvideSubCategoryRecyclerCallback(){
        return (MainActivity) activity;
    }


    @Provides
    @PerActivity
    public SubServiceCategoryRecyclerAdapter ProvideSubServiceCategoryRecyclerAdapter
            (@ActivityContext Context context, List<MainRequests.Services> data
                    , SubServiceCategoryRecyclerAdapter.CallBack callBack) {
        return new SubServiceCategoryRecyclerAdapter(context, data,callBack);
    }

    @Provides
    @PerActivity
    public LinearLayoutManager ProvideLinearLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }


}
