package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateTimeDataModel {

    @Expose
    @SerializedName("date")
    private Date date;

    @Expose
    @SerializedName("time")
    private Time time;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

     public class Date {

        @Expose
        @SerializedName("_date")
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public class Time {

        @Expose
        @SerializedName("_time")
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
