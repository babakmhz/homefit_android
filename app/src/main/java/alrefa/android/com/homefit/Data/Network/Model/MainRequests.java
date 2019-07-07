package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;

import java.util.List;

public class MainRequests {


    @Entity(nameInDb = "sliders")
    public class SliderRequests {

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

    public class CategoriesRequests {

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("title_arabic")
        private String title_arabic;

        @Expose
        @SerializedName("image")
        private String image_url;

        @Expose
        @SerializedName("image_selected")
        private String image_selected_url;

        @Expose
        @SerializedName("service")
        private List<Services> services;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public List<Services> getServices() {
            return services;
        }

        public void setServices(List<Services> services) {
            this.services = services;
        }
    }


    public class Services {

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("title_arabic")
        private String title_arabic;

        @Expose
        @SerializedName("image")
        private String image_url;

        @Expose
        @SerializedName("price")
        private float price;


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
    }

}
