package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "sliders")
public class Slider {

    @Property
    @Expose
    @SerializedName("id")
    private int id;

    @Property
    @Expose
    @SerializedName("phone")
    private String phone;

    @Property
    @Expose
    @SerializedName("location")
    private String location;

    @Property
    @Expose
    @SerializedName("image")
    private String image_url;

    @Property
    @Expose
    @SerializedName("webURL")
    private String webURL;


    @Generated(hash = 2029307854)
    public Slider(int id, String phone, String location, String image_url,
            String webURL) {
        this.id = id;
        this.phone = phone;
        this.location = location;
        this.image_url = image_url;
        this.webURL = webURL;
    }

    @Generated(hash = 932120237)
    public Slider() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }


}
