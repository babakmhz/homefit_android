package alrefa.android.com.homefit.Data.Prefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Utils.AppConstants;

public class AppPreferences implements PrefsHelper{


    private SharedPreferences appSharedPreferences;

//    @Inject
    public AppPreferences(Context activity) {
        appSharedPreferences = activity.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
    }


    public void save_token(String token) {
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.putString(AppConstants.SHARED_PREFS_TOKEN_KEY, token);
        editor.apply();
    }

    public void save_firstLogin(boolean not_first_login) {
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.putBoolean(AppConstants.SHARED_PREFS_FIRST_LOGIN_KEY, not_first_login);
        editor.apply();
    }

    public void save_phone(String phone) {
        SharedPreferences.Editor editor = appSharedPreferences.edit();
        editor.putString(AppConstants.SHARED_PREFS_PHONE_KEY, phone);
        editor.apply();
    }

    public String get_token() {
        return appSharedPreferences.getString(AppConstants.SHARED_PREFS_TOKEN_KEY, "");
    }


    public String get_phone() {
        return appSharedPreferences.getString(AppConstants.SHARED_PREFS_PHONE_KEY, "");
    }
    public boolean get_firstLogin() {
        return appSharedPreferences.getBoolean(AppConstants.SHARED_PREFS_FIRST_LOGIN_KEY,false );
    }

}
