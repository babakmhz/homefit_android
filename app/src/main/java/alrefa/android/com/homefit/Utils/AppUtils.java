package alrefa.android.com.homefit.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import alrefa.android.com.homefit.R;
import alrefa.android.com.homefit.Utils.calendar.CivilDate;
import io.reactivex.Single;
import io.reactivex.SingleSource;


public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    @Nullable
    public static Single<String> getAddress(@NonNull LatLng latLng, @NonNull Geocoder geocoder) {
        try {
            List<Address> addresses;
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                final String address = addresses.get(0).getFeatureName() + ", " +
                        addresses.get(0).getAdminArea();
                return Single.defer(
                        new Callable<SingleSource<? extends String>>() {
                            @Override
                            public SingleSource<? extends String> call() throws Exception {
                                return Single.just(address);
                            }
                        }
                );
            } else return null;
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    @SuppressLint("MissingPermission")
    public static Location getLastKnownLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        else
            return null;
    }

    @SuppressLint("MissingPermission")
    public static void updateLocation(LocationListener listener, Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, listener);
    }


    // TODO: 12/28/18 make open play store function and uncomment this function
   /* public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }
*/
    public static String[] FixDateEnglish(String date) {
        //0 index of array is month name, 1 is monthName,2 is day of month number

        String dateDateName[] = new String[3];
        CivilDate civilDate = new CivilDate();
        civilDate.setYear(Integer.parseInt(date.substring(0, date.indexOf("-"))));
        civilDate.setMonth(Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"))));
        civilDate.setDayOfMonth(Integer.parseInt(date.substring(date.lastIndexOf("-") + 1)));
        dateDateName[0] = getCivilMonthName(civilDate.getMonth());
        dateDateName[1] = String.valueOf(civilDate.getDayOfMonth());
        dateDateName[2] = getEnglishDayOfWeekName(civilDate.getDayOfWeek());
        return dateDateName;
    }

    public static String getCivilMonthName(int monthNumber) {
        String month[] = {"", "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};

        return month[monthNumber];
    }

    public static String getEnglishDayOfWeekName(int dayIndex) {
        String weekDays[] = {"", "Sunday",
                "Monday",
                "Tuesday",
                "Thursday",
                "Wednesday",
                "Friday",
                "Saturday"};
        return weekDays[dayIndex];
    }

    public static String getArabicMonthName(int monthNumber) {
        String month[] = {"",
                "مُحَرَّم",
                "صَفَر",
                "رَبِيع ٱلْأَوَّل",
                "ربيع الثاني",
                "جُمَادَىٰ ٱلْأُولَىٰ",
                "جُمَادَىٰ ٱلْآخِرَة",
                "رَجَب",
                "شَعْبَان",
                "رَمَضَان",
                "شَوَّال",
                "ذُو ٱلْقَعْدَة",
                "ذُو ٱلْحِجَّة"};
        return month[monthNumber];
    }

    public static String fixTime(String time) {
        return time.substring(0, time.lastIndexOf(":"));
    }


    public static void switchToSelectedViewState_date(Context context,
                                                      View container, TextView textdayName, TextView text_date) {
        container.setBackgroundResource(R.drawable.selector_date_choose_selected);
        textdayName.setTextColor(context.getResources().getColor(R.color.colorWhite));
        text_date.setTextColor(context.getResources().getColor(R.color.colorWhite));
    }

    public static void switchToNormalViewState_date(Context context,
                                                    View container,
                                                    TextView textdayName,
                                                    TextView text_date) {
        container.setBackgroundResource(R.drawable.selector_date_choose);
        textdayName.setTextColor(context.getResources().getColor(R.color.black));
        text_date.setTextColor(context.getResources().getColor(R.color.color_default_text_view_text));
    }


    public static void switchToSelectedViewState_time(Context context,
                                                      View container, TextView time) {
        container.setBackgroundResource(R.drawable.selector_date_choose_selected);
        time.setTextColor(context.getResources().getColor(R.color.colorWhite));
    }

    public static void switchToNormalViewState_time(Context context,
                                                    View container,
                                                    TextView time) {
        container.setBackgroundResource(R.drawable.selector_date_choose);
        time.setTextColor(context.getResources().getColor(R.color.black));
    }

    public static String[] fixLocatoinFromString(String location){
        String data[] = new String[2];
        //first index in lat and second index in long
        data[0] = location.substring(0,location.indexOf(","));
        data[1] = location.substring(location.indexOf(",")+1);

        return data;
    }
}
