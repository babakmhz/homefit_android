package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import alrefa.android.com.homefit.Utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerRecyclerAdapter extends RecyclerView.Adapter<DatePickerRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<DateTimeDataModel.Date> datesList;
    private CallBack mCallback;
    private boolean selected = false;

    private int last_selected_position = -1;
    private ViewHolder viewHolder;
    private int i;

    @Inject
    public DatePickerRecyclerAdapter(@ActivityContext Context context,
                                     List<DateTimeDataModel.Date> datesList
            , CallBack mCallback) {
        this.context = context;
        this.datesList = datesList;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public DatePickerRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_date_picker_template, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DatePickerRecyclerAdapter.ViewHolder viewHolder, int i) {
        this.viewHolder = viewHolder;
        this.i = i;
        viewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (datesList != null)
            return datesList.size();
        else return 0;
    }

    public void setData(List<DateTimeDataModel.Date> data) {
        if (datesList.size() > 0){
            datesList.clear();
            selected = false;
        }
        datesList.addAll(data);
        notifyDataSetChanged();
    }

    public interface CallBack {
        void onDateSelected(int last_selected_position,
                            DateTimeDataModel.Date date);

        void onDateRelease();


    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text_date)
        TextView textDate;

        @BindView(R.id.text_dayName)
        TextView textDayName;
        @BindView(R.id.main_container)
        RelativeLayout main_container;


        private int position;
        private DateTimeDataModel.Date date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void onBind(int position) {
            this.position = position;
            super.onBind(position);
            date = datesList.get(position);
            String dateInfo[] = AppUtils.FixDateEnglish(date.getDate());
            textDate.setText(dateInfo[0] + " " + dateInfo[1]);
            textDayName.setText(dateInfo[2]);
        }


        @OnClick(R.id.date_container)
        public void onDateClicked() {
            if (last_selected_position == position) {
                if (selected) {
                    AppUtils.switchToNormalViewState_date(context,
                            main_container, textDayName,
                            textDate);
                    selected = false;
                    mCallback.onDateRelease();
                } else {
                    AppUtils.switchToSelectedViewState_date(context,
                            main_container,
                            textDayName,
                            textDate);
                    selected = true;
                    mCallback.onDateSelected(-1,date);
                }
            } else {
                AppUtils.switchToSelectedViewState_date(context,
                        main_container,
                        textDayName,
                        textDate);
                mCallback.onDateSelected(last_selected_position, date);
                last_selected_position = position;
                selected = true;

            }
        }

        @Override
        protected void clear() {

        }
    }
}
