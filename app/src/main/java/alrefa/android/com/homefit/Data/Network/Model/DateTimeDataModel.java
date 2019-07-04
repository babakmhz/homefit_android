package alrefa.android.com.homefit.Data.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DateTimeDataModel {

    @Expose
    @SerializedName("dates")
    private List<Dates> dates;

    public List<Dates> getDates() {
        return dates;
    }

    public void setDates(List<Dates> dates) {
        this.dates = dates;
    }


    public class Dates {
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
