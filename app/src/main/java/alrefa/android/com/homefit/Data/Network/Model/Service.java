package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "service")
public class Service {

    @Id
    @Expose
    @SerializedName("id")
    private int id;

    @Property
    @Expose
    @SerializedName("title")
    private String title;



    @Property
    @Expose
    @SerializedName("category")
    private int category;

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
    @SerializedName("price")
    private float price;


    @Generated(hash = 555748096)
    public Service(int id, String title, int category, String title_arabic,
            String image_url, float price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.title_arabic = title_arabic;
        this.image_url = image_url;
        this.price = price;
    }

    @Generated(hash = 552382128)
    public Service() {
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle_arabic() {
        return title_arabic;
    }

    public void setTitle_arabic(String title_arabic) {
        this.title_arabic = title_arabic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
