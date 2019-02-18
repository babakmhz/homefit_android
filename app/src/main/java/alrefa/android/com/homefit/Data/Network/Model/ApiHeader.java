package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApiInfo;

public class ApiHeader {

    private PublicApiHeader publicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader) {
        this.publicApiHeader = publicApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return publicApiHeader;
    }

    // TODO: 2/17/19 create private access token class for private access

    public static final class PublicApiHeader {
        @Expose
        @SerializedName("AUTH")
        private String PUBLIC_API_TOKEN;

        @Inject
        public PublicApiHeader() {

        }

        public String getPUBLIC_API_TOKEN() {
            return PUBLIC_API_TOKEN;
        }

        public void setPUBLIC_API_TOKEN(String PUBLIC_API_TOKEN) {
            this.PUBLIC_API_TOKEN = PUBLIC_API_TOKEN;
        }
    }
}

