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

import java.util.List;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ActivityContext;
import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Ui.Base.BaseViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceCategoryRecyclerAdapter extends RecyclerView.Adapter<ServiceCategoryRecyclerAdapter.CategoryViewHolder> {


    private final Context context;
    private final List<Category> categoryDataModels;
    private CallBack mCallback;
    private int last_selected_position = -1;
    private Category last_selected_category = null;

    @Inject
    public ServiceCategoryRecyclerAdapter(@ActivityContext Context context
            , List<Category> categoryDataModels, CallBack mCallback) {
        this.context = context;
        this.categoryDataModels = categoryDataModels;
        this.mCallback = mCallback;
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


    public void addItems(List<Category> data) {
        if (categoryDataModels.size()>0)
            categoryDataModels.clear();
        categoryDataModels.addAll(data);
        notifyDataSetChanged();
    }


    public interface CallBack {
        void onCategoryItemClicked(int selected_position,
                                   int last_selected_position,
                                   Category last_selected_category,
                                   Category category,
                                   Context context);

        void setSelectedServiceId(String id);
    }

    public class CategoryViewHolder extends BaseViewHolder {

        @BindView(R.id.image_category)
        CircleImageView image_category;

        @BindView(R.id.selected_image)
        ImageView selected_icon;

        @BindView(R.id.text_category)
        TextView text_catName;
        private Category each_item;
        private int position;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            this.position = position;
            super.onBind(position);
            each_item = categoryDataModels.get(position);
            Glide.with(context).load(each_item.getImage_url())
                    .centerCrop()
                    .into(image_category);

            text_catName.setText(each_item.getTitle());

        }

        @OnClick(R.id.lcontainer)
        public void onItemClicked() {
            mCallback.onCategoryItemClicked(position,
                    last_selected_position,
                    last_selected_category
                    , each_item,
                    context);

            last_selected_category = each_item;
            last_selected_position = position;

            // TODO: 2/28/19 use animation on loading image in MainActivity
            Glide.with(context).
                    load(each_item.getImage_selected_url())
                    .centerCrop().dontAnimate()
                    .into(image_category);


            mCallback.setSelectedServiceId(String.valueOf(each_item.getId()));
        }

        public void clearData(){
            clear();
        }

        @Override
        protected void clear() {
            image_category.setImageDrawable(null);
            text_catName.setText("");
        }
    }
}
