package alrefa.android.com.homefit.Ui.Main;


import android.view.View;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.Ui.Base.MvpView;
import alrefa.android.com.homefit.Utils.AppUtils;

public interface BottomSheetMvpView extends MvpView {

    String getService_id();
    void onAvailableDatesFetched(List<DateTimeDataModel.Date> models);
    void onAvailableTimesFetched(List<DateTimeDataModel.Time> models);
    void onProvidersPrepared(List<ProvidersDataModel> ProvidersDataModels);
    View getRootView();
    void showDateTimeProgress();
    void hideDateTimeProgress();


}
