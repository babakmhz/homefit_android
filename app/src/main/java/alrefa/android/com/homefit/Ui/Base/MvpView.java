
package alrefa.android.com.homefit.Ui.Base;


import android.support.annotation.StringRes;
import android.view.View;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(String message);

    void onError(@StringRes int resId);

    void showMessage(View container, String message);

    void showMessage(View container,@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    void openActivityOnTokenExpire();

}
