package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainRequests {

    public static final class SliderRequests{

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("phone")
        private String phone;

        @Expose
        @SerializedName("location")
        private String location;

        @Expose
        @SerializedName("image")
        private String image_url;

        @Expose
        @SerializedName("image_arabic")
        private String image_arabic_url;


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

        public String getImage_arabic_url() {
            return image_arabic_url;
        }

        public void setImage_arabic_url(String image_arabic_url) {
            this.image_arabic_url = image_arabic_url;
        }
    }
}
