package alrefa.android.com.homefit.Data.DB;

import java.util.List;

import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.Service;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
import io.reactivex.Observable;

public interface DatabaseHelper {

    //here are database methods

    Observable<Long> insertSliders(final Slider model);

    Observable<List<Slider>> getSlidersFromDb();

    Observable<Long> insertCategories(final Category model);

    Observable<List<Category>> getCategoriesFromDb();

    Observable<List<Service>> getSubCategoriesFromDb(long categoryId);


}
