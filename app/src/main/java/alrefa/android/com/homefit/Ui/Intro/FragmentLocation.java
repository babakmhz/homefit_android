package alrefa.android.com.homefit.Ui.Intro;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseFragment;
import alrefa.android.com.homefit.Utils.AppUtils;
import butterknife.ButterKnife;

public class FragmentLocation extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AppUtils.askLocationPermission(getActivity());
        }
    }

    @Override
    public void showMessage(View container, String message) {

    }
}
