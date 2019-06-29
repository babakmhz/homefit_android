package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.DateTimeDataModel;
import alrefa.android.com.homefit.Data.Network.Model.providersDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import alrefa.android.com.homefit.Utils.AppLogger;
import alrefa.android.com.homefit.Utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProvidersRecyclerAdapter extends RecyclerView.Adapter<ProvidersRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<providersDataModel> providersDataModels;

    @Inject
    public ProvidersRecyclerAdapter(@ActivityContext Context context, List<providersDataModel> providersDataModels) {
        this.context = context;
        this.providersDataModels = providersDataModels;
    }

    @NonNull
    @Override
    public ProvidersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_service_providers_template, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProvidersRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (providersDataModels != null)
            return providersDataModels.size();
        else return 0;
    }

    public void setData(List<providersDataModel> data) {
        if (providersDataModels.size() > 0)
            providersDataModels.clear();
        providersDataModels.addAll(data);
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

        }



        @Override
        protected void clear() {

        }
    }
}
