package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class TimePickerRecyclerAdapter extends RecyclerView.Adapter<TimePickerRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<DateTimeDataModel.Time> timesList;
    private CallBack mCallback;
    private boolean selected = false;
    private int last_selected_position = -1;
    private DateTimeDataModel.Time time;

    @Inject
    public TimePickerRecyclerAdapter(@ActivityContext Context context
            , List<DateTimeDataModel.Time> timesList,
                                     CallBack mCallback) {
        this.context = context;
        this.timesList = timesList;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public TimePickerRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_time_picker_template, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimePickerRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (timesList != null)
            return timesList.size();
        else return 0;
    }

    public void setData(List<DateTimeDataModel.Time> data) {
        if (timesList.size() > 0){
            timesList.clear();
            selected = false;
        }
        timesList.addAll(data);
        notifyDataSetChanged();
    }

    public interface CallBack {

        void onTimeSelected(int last_selected_position,
                            DateTimeDataModel.Time time);

        void onTimeRelease();
    }

    public class ViewHolder extends BaseViewHolder {


        @BindView(R.id.text_time)
        TextView textTime;
        private int position;

        @BindView(R.id.time_container)
        LinearLayout time_container;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            super.onBind(position);
            time = timesList.get(position);
            textTime.setText(AppUtils.fixTime(time.getTime()));
        }

        @OnClick(R.id.time_container)
        public void onTimeClicked() {
            if (last_selected_position == position) {
                if (selected) {
                    AppUtils.switchToNormalViewState_time(context,
                            time_container, textTime);
                    selected = false;
                    mCallback.onTimeRelease();
                } else {
                    AppUtils.switchToSelectedViewState_time(context,
                            time_container,
                            textTime);
                    selected = true;
                    mCallback.onTimeSelected(-1,time);
                }
            } else {
                AppUtils.switchToSelectedViewState_time(context,
                        time_container,
                        textTime);
                mCallback.onTimeSelected(last_selected_position, time);
                last_selected_position = position;
                selected = true;

            }
        }

        @Override
        protected void clear() {

        }
    }
}
