package com.nitramite.sairaankallis;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fingrid {

    // Logging
    private final static String TAG = Fingrid.class.getSimpleName();

    private OkHttpClient client = new OkHttpClient();
    private String DATA_API_URL = "https://www.fingrid.fi/api/graph/power-system-state?language=fi";
    private FingridInterface fingridInterface;


    // Constructor
    public Fingrid(FingridInterface fingridInterface_) {
        fingridInterface = fingridInterface_;
    }


    public void GetGridData() {
        Log.i(TAG, "Grid data update started");
        Thread thread = new Thread(() -> {
            Request request = new Request.Builder()
                    .url(DATA_API_URL)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                GridData gridData = ParseGridData(response.body().string());
                this.fingridInterface.getDataSuccess(gridData);
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                this.fingridInterface.getDataFailed(e.toString());
            }
        });
        thread.start();
    }


    private GridData ParseGridData(final String responseData) {
        GridData gridData = new GridData();

        Log.i(TAG,  responseData);


        return gridData;
    }


    public class GridData {
        public GridData() {

        }
    }

}
