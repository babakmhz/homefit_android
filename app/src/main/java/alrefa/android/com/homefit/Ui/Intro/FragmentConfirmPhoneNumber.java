package alrefa.android.com.homefit.Ui.Intro;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentConfirmPhoneNumber extends BaseFragment {

    public static boolean is_phone_confirmed = false;

    @BindView(R.id.editText_confirm)
    EditText editTextConfirm;
//
//    @BindView(R.id.progress_sms)
//    ProgressBar progressBar;
    int progress = 0;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_confirm_phone, container, false);
        ButterKnife.bind(this, view);

//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                Toast.makeText(getActivity(), String.valueOf(progress), Toast.LENGTH_SHORT).show();
//                progress++;
//            }
//        };
//
//        timer.schedule(timerTask,1000);
        return view;

    }

    public EditText getCodeEditText(){
        return this.editTextConfirm;
    }

    public View getView(){
        return this.view;
    }
    @Override
    protected void setUp(View view) {

    }
}
