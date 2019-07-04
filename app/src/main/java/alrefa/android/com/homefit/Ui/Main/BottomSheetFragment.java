package alrefa.android.com.homefit.Ui.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Component.ActivityComponent;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseBottomSheetFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomSheetFragment
        extends BaseBottomSheetFragment implements
        BottomSheetMvpView {


    @Inject
    MainActivityMvpView mainActivityMvpView;


    @BindView(R.id.time_indicator_container)
    LinearLayout timeIndicatorContainer;

    @BindView(R.id.date_indicator_container)
    LinearLayout dateIndicatorContainer;

    @BindView(R.id.recycler_provider)
    RecyclerView recyclerProviders;

    @BindView(R.id.scroll_bottomSheet)
    NestedScrollView scrollView;

    @BindView(R.id.recycler_date)
    RecyclerView recyclerDate;

    @BindView(R.id.recycler_time)
    RecyclerView recyclerTime;



    @Inject
    ProvidersRecyclerAdapter providersRecyclerAdapter;

    @Inject
    TimePickerRecyclerAdapter timePickerRecyclerAdapter;

    @Inject
    BottomSheetMvpPresenter<BottomSheetMvpView> mPresenter;
    @Inject
    DatePickerRecyclerAdapter datePickerRecyclerAdapter;
    private View view;
    private String service_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bottom_sheet_view_base, container, false);
        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            activityComponent.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void setUp(View view) {
//        mPresenter.getDateTime();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mPresenter.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mPresenter.onDetach();
    }

    @Override
    public String getService_id() {
        return mainActivityMvpView.getSelectedServiceIdFromActivity();
    }


    @Override
    public void onAvailableDatesFetched(List<DateTimeDataModel.Date> models) {
        if (recyclerDate.getVisibility() == View.GONE)
            recyclerDate.setVisibility(View.VISIBLE);

        if (dateIndicatorContainer.getVisibility() == View.GONE)
            dateIndicatorContainer.setVisibility(View.VISIBLE);
        datePickerRecyclerAdapter.setData(models);
        recyclerDate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerDate.setAdapter(datePickerRecyclerAdapter);
    }

    @Override
    public void onAvailableTimesFetched(List<DateTimeDataModel.Time> models) {
        if (recyclerTime.getVisibility() == View.GONE)
            recyclerTime.setVisibility(View.VISIBLE);

        if (timeIndicatorContainer.getVisibility() == View.GONE)
            timeIndicatorContainer.setVisibility(View.VISIBLE);

        timePickerRecyclerAdapter.setData(models);
        recyclerTime.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerTime.setAdapter(timePickerRecyclerAdapter);
        scrollView.scrollTo(0,100);
    }

    public RecyclerView getProviderRecycler() {
        return this.recyclerProviders;
    }

    public RecyclerView getDatePickerRecycler() {
        return this.recyclerDate;
    }

    public RecyclerView getTimeRecycler() {
        return this.recyclerTime;
    }


    @Override
    public void onProvidersPrepared(List<ProvidersDataModel> ProvidersDataModels) {
        providersRecyclerAdapter.setData(ProvidersDataModels);
        recyclerProviders.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerProviders.setAdapter(providersRecyclerAdapter);
    }
}
