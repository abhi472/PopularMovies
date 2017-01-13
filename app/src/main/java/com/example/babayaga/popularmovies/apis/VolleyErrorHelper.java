package com.example.babayaga.popularmovies.apis;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.babayaga.popularmovies.R;

import org.json.JSONObject;

/**
 * Created by abhi on 1/13/17.
 */

public class VolleyErrorHelper {
    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param context
     * @return
     */

    static private String message;
    static private String errorMessage;

    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.timeout);
        } else if (isServerProblem(error)) {
            return handleServerError(error, context);

        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.nointernet);
        }
        return context.getResources().getString(R.string.generic_error);

    }

    private static String handleServerError(Object error, Context context) {

        VolleyError er = (VolleyError) error;
        NetworkResponse response = er.networkResponse;
        if (response != null) {
            switch (response.statusCode) {

                case 404:
                case 422:
                case 401:
            try {
                // server might return error like this { "error": "Some error occured" }
                // Use "Gson" to parse the result

                JSONObject jo = new JSONObject(new String(response.data));
                message = jo.getString("message");
                JSONObject jo2 =  jo.getJSONObject("error");
                JSONObject jo3 = jo2.getJSONObject("email");
                errorMessage = jo3.getString("message");



                if (errorMessage!=null)
                    return message +"\n"+errorMessage;
                else return message;

            } catch (Exception e) {
                e.printStackTrace();
            }
            // invalid request
            return ((VolleyError) error).getMessage();

            default:
              return context.getResources().getString(R.string.timeout);
             }
        }

        return context.getResources().getString(R.string.generic_error);
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError || error instanceof AuthFailureError);
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError || error instanceof NoConnectionError);
    }
}

