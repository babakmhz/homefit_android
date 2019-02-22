package alrefa.android.com.homefit.Ui.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.MainRequests;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceCategoryRecyclerAdapter extends RecyclerView.Adapter<ServiceCategoryRecyclerAdapter.CategoryViewHolder> {


    private final Context context;
    private final List<MainRequests.CategoriesRequests> categoryDataModels;
    private CallBack mCallback;

    public ServiceCategoryRecyclerAdapter(@ActivityContext Context context, List<MainRequests.CategoriesRequests> categoryDataModels) {
        this.context = context;
        this.categoryDataModels = categoryDataModels;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_categories_template, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        if (categoryDataModels != null && categoryDataModels.size() > 0)
            return categoryDataModels.size();
        else
            return 0;
    }


    public void addItems(List<MainRequests.CategoriesRequests> data) {
        categoryDataModels.addAll(data);
        notifyDataSetChanged();
    }


    public interface CallBack {

    }

    public class CategoryViewHolder extends BaseViewHolder {


        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            MainRequests.CategoriesRequests dataModel = categoryDataModels.get(position);


        }


        @OnClick(R.id.cat_item)
        public void onItemClicked() {
            if (mCallback != null) {

            }
        }

        @Override
        protected void clear() {

        }
    }
}
