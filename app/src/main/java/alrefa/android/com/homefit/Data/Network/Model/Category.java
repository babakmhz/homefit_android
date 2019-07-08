package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity(nameInDb = "Category")
public class Category {

    @Id
    @Expose
    @SerializedName("id")
    private long id;

    @Property
    @Expose
    @SerializedName("title")
    private String title;

    @Property
    @Expose
    @SerializedName("title_arabic")
    private String title_arabic;

    @Property
    @Expose
    @SerializedName("image")
    private String image_url;

    @Property
    @Expose
    @SerializedName("image_selected")
    private String image_selected_url;

    @ToMany(referencedJoinProperty = "id")
    @Expose
    @SerializedName("service")
    private List<Service> services;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 40161530)
    private transient CategoryDao myDao;

    @Generated(hash = 587392174)
    public Category(long id, String title, String title_arabic, String image_url,
                    String image_selected_url) {
        this.id = id;
        this.title = title;
        this.title_arabic = title_arabic;
        this.image_url = image_url;
        this.image_selected_url = image_selected_url;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_selected_url() {
        return image_selected_url;
    }

    public void setImage_selected_url(String image_selected_url) {
        this.image_selected_url = image_selected_url;
    }

    public String getTitle_arabic() {
        return title_arabic;
    }

    public void setTitle_arabic(String title_arabic) {
        this.title_arabic = title_arabic;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1521497359)
    public List<Service> getServices() {
        if (services == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ServiceDao targetDao = daoSession.getServiceDao();
            List<Service> servicesNew = targetDao._queryCategory_Services(id);
            synchronized (this) {
                if (services == null) {
                    services = servicesNew;
                }
            }
        }
        return services;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1227775301)
    public synchronized void resetServices() {
        services = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 503476761)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoryDao() : null;
    }


}
