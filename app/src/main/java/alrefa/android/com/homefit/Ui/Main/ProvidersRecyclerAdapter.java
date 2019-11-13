package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.ProvidersDataModel;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProvidersRecyclerAdapter extends RecyclerView.Adapter<ProvidersRecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<ProvidersDataModel> providersDataModels;
    private CallBack mCallback;
    private int last_selected_position = -1;
    private boolean selected = false;
    private boolean first_time_choose = true;


    @Inject
    public ProvidersRecyclerAdapter(@ActivityContext Context context,
                                    List<ProvidersDataModel> providersDataModels,
                                    CallBack mCallback) {
        this.context = context;
        this.providersDataModels = providersDataModels;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public ProvidersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_service_providers_template, viewGroup, false));
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

    public void setData(List<ProvidersDataModel> data) {
        if (providersDataModels.size() > 0) {
            providersDataModels.clear();
            selected = false;
        }
        providersDataModels.addAll(data);
        notifyDataSetChanged();
    }

    public interface CallBack {

        void onProviderSelected(int last_selected_position, ProvidersDataModel provider);
        void onProviderRelease();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.text_provider_title)
        TextView text_provider_title;

        @BindView(R.id.view_dark_indicator)
        View view_dark_indicator;

        @BindView(R.id.image_provider)
        ImageView image_provider;

        @BindView(R.id.selected_mark)
        ImageView image_selected;


        @BindView(R.id.text_provider_cost)
        TextView text_provider_cost;
        private int position;
        private ProvidersDataModel model;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            super.onBind(position);
//            AppLogger.d("provider",model.getName());
            model = providersDataModels.get(position);
            text_provider_title.setText(model.getName());
            text_provider_cost.setText(String.valueOf(model.getTotalCost())+"$");
            Glide.with(context).load(model.getProfile_photo()).centerCrop()
                    .into(image_provider);

        }


        @OnClick(R.id.provider_container)
        public void onProvider_click() {
            if (last_selected_position == position ) {
                if (selected){
                    image_selected.setVisibility(View.GONE);
                    view_dark_indicator.setVisibility(View.GONE);
                    selected = false;
                    mCallback.onProviderRelease();
                }else{
                    image_selected.setVisibility(View.VISIBLE);
                    view_dark_indicator.setVisibility(View.VISIBLE);
                    selected = true;
                    mCallback.onProviderSelected(-1,model);
                }
            } else {
                image_selected.setVisibility(View.VISIBLE);
                view_dark_indicator.setVisibility(View.VISIBLE);
                mCallback.onProviderSelected(last_selected_position, model);
                last_selected_position = position;
                selected = true;
            }

        }


        @Override
        protected void clear() {

        }
    }
}
