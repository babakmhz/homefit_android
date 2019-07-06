package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDataModel {


    @Expose
    @SerializedName("services")
    private String services;

    @Expose
    @SerializedName("dateTime")
    private String dateTime;


    @Expose
    @SerializedName("location")
    private String location;


    @Expose
    @SerializedName("provider")
    private int provider;


    @Expose
    @SerializedName("description")
    private String description;


    @Expose
    @SerializedName("total_cost")
    private String total_cost;


    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public class Response {
        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("services")
        private String services;

        @Expose
        @SerializedName("dateTime")
        private String dateTime;

        @Expose
        @SerializedName("orderNumber")
        private String orderNumber;


        @Expose
        @SerializedName("location")
        private String location;


        @Expose
        @SerializedName("client")
        private String client;


        @Expose
        @SerializedName("provider")
        private int provider;

        @Expose
        @SerializedName("status")
        private String status;


        @Expose
        @SerializedName("qrCode")
        private String qrCode;


        @Expose
        @SerializedName("description")
        private String description;


        @Expose
        @SerializedName("total_cost")
        private String total_cost;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public int getProvider() {
            return provider;
        }

        public void setProvider(int provider) {
            this.provider = provider;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(String total_cost) {
            this.total_cost = total_cost;
        }
    }

}
