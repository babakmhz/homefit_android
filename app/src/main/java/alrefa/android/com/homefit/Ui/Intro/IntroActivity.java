package alrefa.android.com.homefit.Ui.Intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;

import alrefa.android.com.homefit.Data.Prefs.AppPreferences;
import alrefa.android.com.homefit.Ui.Splash.SplashScreenActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroActivity extends AppIntro2 {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new FragmentChooseLanguage());
        addSlide(new FragmentWelcome());
//        addSlide(new FragmentExperts());
        addSlide(new FragmentLocation());
        addSlide(new FragmentRegister());
        addSlide(new FragmentConfirmPhoneNumber());
        showSkipButton(false);
        showStatusBar(false);

//        setProgressButtonEnabled(false);
//        setBarColor(Color.parseColor("#"));
//        setSeparatorColor(Color.parseColor("#2196F3"));
//        setVibrate(true);
//        setVibrateIntensity(30);

//        setFadeAnimation();
//        setZoomAnimation();
//        setFlowAnimation();
//        setSlideOverAnimation();
//        setDepthAnimation();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        FragmentConfirmPhoneNumber confirmPhoneNumber = (FragmentConfirmPhoneNumber) currentFragment;
        if ("0000".equals(confirmPhoneNumber.getCodeEditText().getText().toString())){
            AppPreferences appPreferences = new AppPreferences(this);
            appPreferences.save_firstLogin(true);
            startActivity(new Intent(this, SplashScreenActivity.class));
            finish();
        }else
            Snackbar.make(confirmPhoneNumber.getView(),"Wrong Code!",Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
