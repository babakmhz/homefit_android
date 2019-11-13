package alrefa.android.com.homefit.Ui.Intro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.paolorotolo.appintro.ISlidePolicy;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentRegister extends BaseFragment implements ISlidePolicy {


    @BindView(R.id.editText_phone)
    EditText editTextPhone;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public boolean isPolicyRespected() {
        return editTextPhone != null &&
                !"".equals(editTextPhone.getText().toString());
        // TODO: 7/9/19 do phone number validation
    }

    @Override
    public void onUserIllegallyRequestedNextPage() {
        if (getActivity()!=null)
        Snackbar.make(view, getActivity().getString(R.string.Please_Enter_Your_Phone_Number_To_Proceed), Snackbar.LENGTH_LONG).show();
    }
}
