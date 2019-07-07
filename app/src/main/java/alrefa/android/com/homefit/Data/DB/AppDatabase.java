package alrefa.android.com.homefit.Data.DB;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import alrefa.android.com.homefit.BuildConfig;
import alrefa.android.com.homefit.Data.Network.Model.Category;
import alrefa.android.com.homefit.Data.Network.Model.DaoMaster;
import alrefa.android.com.homefit.Data.Network.Model.DaoSession;
import alrefa.android.com.homefit.Data.Network.Model.Service;
import alrefa.android.com.homefit.Data.Network.Model.ServiceDao;
import alrefa.android.com.homefit.Data.Network.Model.Slider;
import alrefa.android.com.homefit.Utils.rx.SchedulerProvider;
import io.reactivex.Observable;


public class AppDatabase implements DatabaseHelper {

    private final DaoSession daoSession;
    private SchedulerProvider schedulerProvider;

    @Inject
    public AppDatabase(DbOpenHelper openHelper, SchedulerProvider schedulerProvider) {
        daoSession = new DaoMaster(openHelper.getWritableDb()).newSession();
        this.schedulerProvider = schedulerProvider;
        // TODO: 8/26/18 remove these two lines for release
        if (BuildConfig.DEBUG) {
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    @Override
    public Observable<Long> insertSliders(final Slider model) {

        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                //first we delete existing sliders in database
                daoSession.getSliderDao().deleteAll();
                //inserting sliders loaded from server in db
                return daoSession.getSliderDao().insert(model);
            }
        }).subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.ui());
    }

    @Override
    public Observable<List<Slider>> getSlidersFromDb() {
        return Observable.fromCallable(new Callable<List<Slider>>() {
            @Override
            public List<Slider> call() throws Exception {
                return daoSession.getSliderDao().loadAll();
            }
        }).subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.ui());
    }

    @Override
    public Observable<Long> insertCategories(final Category model) {

        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                // TODO: 8/26/18 surround with try-cache
                // clear databases first to store new items
                daoSession.getCategoryDao().deleteAll();
                // getting services list for one category entity & setting service_tag = category_id
                List<Service> service = model.getServices();
                // serviceDataModel is related entity defined as list in category dataModel
                Service servicesDataModel = new Service();
                try {
                    daoSession.getServiceDao().queryBuilder().
                            where(ServiceDao.Properties.Category.eq(model.getId())).buildDelete()
                            .executeDeleteWithoutDetachingEntities();
                    servicesDataModel.setCategory(model.getId());
                    for (int i = 0; i < service.size(); i++) {
                        // inserting all services model for one category entity
                        servicesDataModel.setTitle(service.get(i).getTitle());
                        servicesDataModel.setTitle_arabic(service.get(i).getTitle_arabic());
                        servicesDataModel.setId(service.get(i).getId());
                        servicesDataModel.setPrice(service.get(i).getPrice());
                        servicesDataModel.setImage_url(service.get(i).getImage_url());
                        daoSession.insert(servicesDataModel);
                    }
                    // inserting for category entity
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return daoSession.insert(model);
            }
        }).subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.ui());
//        return null;
    }


    @Override
    public Observable<List<Category>> getCategoriesFromDb() {
        return Observable.fromCallable(new Callable<List<Category>>() {
            @Override
            public List<Category> call() throws Exception {
                return daoSession.getCategoryDao().loadAll();
            }
        }).subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.ui());
    }

    @Override
    public Observable<List<Service>> getSubCategoriesFromDb(final long categoryId) {
        // it is important to clear daoSession cache before get lists
        daoSession.clear();
        return Observable.fromCallable(new Callable<List<Service>>() {
            @Override
            public List<Service> call() throws Exception {
                return daoSession.getServiceDao().queryBuilder()
                        .where(ServiceDao.Properties.Category.eq(categoryId)).list();
            }
            // TODO: 8/26/18 subscribeOn new thread can be switched to io
        }).subscribeOn(schedulerProvider.newThread())
                .observeOn(schedulerProvider.ui());
    }


}
