package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvidersDataModel {

    @Expose
    @SerializedName("id")
    private long id;


    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("total_cost")
    private float totalCost;

    @Expose
    @SerializedName("profile_photo")
    private String profile_photo;


    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
