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

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

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
}
