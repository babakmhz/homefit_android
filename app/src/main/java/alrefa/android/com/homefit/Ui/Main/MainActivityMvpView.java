package alrefa.android.com.homefit.Ui.Main;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.Ui.Base.MvpView;

public interface MainActivityMvpView extends MvpView {

    void onSlidersPrepared(List<MainRequests.SliderRequests> sliders);
    void onAvailableServicesPrepared(List<MainRequests.CategoriesRequests> sliders);
}
