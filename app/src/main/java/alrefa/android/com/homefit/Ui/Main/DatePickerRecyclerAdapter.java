package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DatePickerRecyclerAdapter extends RecyclerView.Adapter<DatePickerRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<DateTimeDataModel> datesList;

    @Inject
    public DatePickerRecyclerAdapter(@ActivityContext Context context, List<DateTimeDataModel> datesList) {
        this.context = context;
        this.datesList = datesList;
    }

    @NonNull
    @Override
    public DatePickerRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_date_picker_template, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DatePickerRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (datesList != null)
            return datesList.size();
        else return 0;
    }

    public void setData(List<DateTimeDataModel> data) {
        if (datesList.size()>0)
            datesList.clear();
        datesList.addAll(data);
        notifyDataSetChanged();
    }

    public interface CallBack {

    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text_date)
        TextView textDate;

        @BindView(R.id.text_dayName)
        TextView textDayName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            DateTimeDataModel datetime = datesList.get(position);
            textDate.setText(datetime.getDate().getDate());
            textDayName.setText(datetime.getTime().getTime());
        }

        @Override
        protected void clear() {

        }
    }
}
