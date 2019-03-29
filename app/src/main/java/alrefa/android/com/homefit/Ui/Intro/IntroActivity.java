package alrefa.android.com.homefit.Ui.Intro;

import android.os.Bundle;
import android.view.View;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseActivity;
import butterknife.ButterKnife;

public class IntroActivity extends BaseActivity {
    @Override
    public void showMessage(View container, String message) {
        super.showMessage(container, message);
    }

    @Override
    public void showMessage(View container, int resId) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void setUp() {
        setUnBinder(ButterKnife.bind(this));
        getActivityComponent().inject(this);

    }
}
