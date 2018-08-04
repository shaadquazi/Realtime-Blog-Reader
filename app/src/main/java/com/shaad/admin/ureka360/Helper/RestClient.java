package com.shaad.admin.ureka360.Helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public static final String SHARED_PREFS_FILE = "Ureka360_Pref";
    public static final String BASE_URL = "https://ureka360.com//wp-json/wp/v2/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
