package alrefa.android.com.homefit.Ui.Intro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintro.ISlidePolicy;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentChooseLanguage extends BaseFragment {

    private View view;

    private boolean is_english_botton_clicked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_language, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.button_english_language)
    public void onButtonEnglishClicked() {

        is_english_botton_clicked = true;

    }


    @OnClick(R.id.button_arabic_language)
    public void onButtonArabicClicked() {
        Snackbar.make(view, "Arabic Language Is Not Available In Demo Version"
                , Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void setUp(View view) {

    }

    @Override
    public void showMessage(View container, String message) {

    }
//
//    @Override
//    public boolean isPolicyRespected() {
//        return is_english_botton_clicked;
//    }
//
//    @Override
//    public void onUserIllegallyRequestedNextPage() {
//
//        Snackbar.make(view, "Please Choose Your Language First"
//                , Snackbar.LENGTH_LONG).show();
//    }
}
