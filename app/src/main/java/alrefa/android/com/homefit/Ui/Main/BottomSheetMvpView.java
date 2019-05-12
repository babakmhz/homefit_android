package alrefa.android.com.homefit.Ui.Main;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Ui.Base.MvpView;

public interface BottomSheetMvpView extends MvpView {

    String getService_id();
    void onAvailableDateTimesFetched(List<DateTimeDataModel> models);
}
