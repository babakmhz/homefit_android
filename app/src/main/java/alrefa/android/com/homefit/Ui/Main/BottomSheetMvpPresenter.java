package alrefa.android.com.homefit.Ui.Main;

import alrefa.android.com.homefit.Ui.Base.MvpPresenter;

public interface BottomSheetMvpPresenter<V extends BottomSheetMvpView> extends MvpPresenter<V> {

    void getDateTime(String service_id);

}
