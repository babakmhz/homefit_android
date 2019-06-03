package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubServiceCategoryRecyclerAdapter extends RecyclerView.Adapter<SubServiceCategoryRecyclerAdapter.CategoryViewHolder> {


    private final Context context;
    private final List<MainRequests.Services> servicesDataModels;
    private CallBack mCallback;
    private int last_selected_position = -1;
    private MainRequests.Services last_selected_category = null;
    private List<MainRequests.Services> selected_services
            = new ArrayList<>();

    public SubServiceCategoryRecyclerAdapter(@ActivityContext Context context
            , List<MainRequests.Services> servicesDataModels, CallBack mCallback) {
        this.context = context;
        this.servicesDataModels = servicesDataModels;
        this.mCallback = mCallback;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_sub_categories_template, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (servicesDataModels != null && servicesDataModels.size() > 0)
            return servicesDataModels.size();
        else
            return 0;
    }


    public void addItems(List<MainRequests.Services> data) {
        if (servicesDataModels.size()>0)
            servicesDataModels.clear();
        servicesDataModels.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        servicesDataModels.clear();
        notifyDataSetChanged();
    }

    public List<MainRequests.Services> getServices(){
        return this.selected_services;
    }


    public interface CallBack {
        void onSubCategoryItemClicked();

    }


    public class CategoryViewHolder extends BaseViewHolder {


        @BindView(R.id.image_service)
        ImageView image_service;
        @BindView(R.id.text_service_title)
        TextView text_serviceTitle;
//        @BindView(R.id.text_service_price)
//        TextView text_servicePrice;
        @BindView(R.id.view_dark_indicator)
        View view_darkIndicator;
        @BindView(R.id.selected_mark)
        ImageView selected_mark;
        private MainRequests.Services each_item;
        private int position;



        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            super.onBind(position);
            each_item = servicesDataModels.get(position);
            // TODO: 3/1/19 implement animation for loading this image
            Glide.with(context).load(each_item.getImage_url())
                    .into(image_service);

            text_serviceTitle.setText(each_item.getTitle());
            // TODO: 2/28/19 handle costs for each currency (libraries in notes.txt file)
//            text_servicePrice.setText(String.valueOf(each_item.getPrice()) + "$");

        }

        @OnClick(R.id.service_container)
        public void onItemClicked() {
//            mCallback.onSubCategoryItemClicked();
            // TODO: 3/2/19 add items to list
            if (!selected_services.contains(each_item)) {
                selected_services.add(each_item);
                view_darkIndicator.setVisibility(View.VISIBLE);
                selected_mark.setVisibility(View.VISIBLE);
            } else {
                selected_services.remove(each_item);
                view_darkIndicator.setVisibility(View.GONE);
                selected_mark.setVisibility(View.GONE);
            }
        }

        @Override
        protected void clear() {

        }
    }
}
