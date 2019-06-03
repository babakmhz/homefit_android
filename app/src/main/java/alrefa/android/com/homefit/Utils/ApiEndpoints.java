package alrefa.android.com.homefit.Utils;

import android.os.Build;

import alrefa.android.com.homefit.BuildConfig;

public final class ApiEndpoints {

    // TODO: 2/20/19 change baseURL in build config for release version
    public static final String HEADER_AUTH_KEY = "AUTHORIZATION";
    public static final String SLIDER_ENDPOINT = BuildConfig.BASE_URL + "sliders";
    public static final String SERVICE_DATETIMIE_ENDPOINT = BuildConfig.BASE_URL+"availableServiceDate";
    public static final String CATEGORIES_ENDPOINT = BuildConfig.BASE_URL + "serviceCategories";
    public static final String AVAILABLE_PROVIDERS_ENDPOINT = BuildConfig.BASE_URL + "getProviders";
}
