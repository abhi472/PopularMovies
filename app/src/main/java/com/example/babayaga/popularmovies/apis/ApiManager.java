package com.example.babayaga.popularmovies.apis;

/**
 * Created by abhi on 1/13/17.
 */

import android.content.Context;
import android.nfc.Tag;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.nfc.Tag;
import android.support.v4.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.babayaga.popularmovies.callbacks.AsyncTaskCompleteListener;
import com.example.babayaga.popularmovies.callbacks.ICallBack;
import com.example.babayaga.popularmovies.callbacks.ICallback2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class ApiManager {

    public static final int MaxTimeOut = 30000;

    static ApiManager apiManager;
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    private ICallBack iCallback;
    private Fragment f;

    // Constructor
    public ApiManager() {
    }

    // Constructor
    public ApiManager(Fragment fragment) {
        f = fragment;
    }

    // Get Constructor
    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    // used for call from Fragment and implement callback
    public static ApiManager newInstance(Fragment fragment) {
        apiManager = new ApiManager(fragment);
        return apiManager;
    }

    public void requestGet(Context ctx, String url) {
        asyncTaskCompleteListener = (AsyncTaskCompleteListener) ctx;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onComplete(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                asyncTaskCompleteListener.onError(error);
            }

        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void requestGet(Context ctx, String url, int requestId) {
        asyncTaskCompleteListener = (AsyncTaskCompleteListener) ctx;
        final int myRequestId = requestId;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onComplete(response,
                                myRequestId);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                asyncTaskCompleteListener.onError(error.toString(),
                        myRequestId);
            }
        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void requestGet(Context ctx, String url, final String auth) {
        asyncTaskCompleteListener = (AsyncTaskCompleteListener) ctx;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onComplete(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                asyncTaskCompleteListener.onError(error);
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization",auth);
                //..add other headers
                return params;
            }


        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private Map<String, String> checkParams(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = it.next();
            if (pairs.getValue() == null) {
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }

    public void requestPost(Context ctx, String url, Map<String, String> params, final String auth) {
        final Map<String, String> _params = params;
        asyncTaskCompleteListener = (AsyncTaskCompleteListener) ctx;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onComplete(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                asyncTaskCompleteListener.onError(error);
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                return checkParams(_params);
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization",auth);
                //..add other headers
                return params;
            }


        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void requestPost(Context ctx, String url, Map<String, String> params, int irequestId) {
        final int myRequestId = irequestId;
        final Map<String, String> _params = params;
        asyncTaskCompleteListener = (AsyncTaskCompleteListener) ctx;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onComplete(response,
                                myRequestId);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                asyncTaskCompleteListener.onError(VolleyErrorHelper.getMessage(error,f.getContext()),myRequestId);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return checkParams(_params);
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    //==============================
    // used get for fragment only
    //==============================
    public void requestGet(String url) {

        iCallback = (ICallBack) f;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(iCallback != null) {
                            iCallback.onRecieve(response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(iCallback != null) {
                    iCallback.onError(error.getMessage());
                }
            }
        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
    public void requestGet(String url, final int requestId) {

        iCallback = (ICallBack) f;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(iCallback != null) {
                            iCallback.onRecieve(response,requestId);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(iCallback != null) {
                    iCallback.onError(error.getMessage(),requestId);
                }
            }
        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    public void requestGet(String url, final String auth) {
        iCallback = (ICallBack) f;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        iCallback.onRecieve(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                iCallback.onError(error.getMessage());
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization",auth);
                //..add other headers
                return params;
            }


        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    //==============================
    // used post for fragment only
    //==============================

    public void requestPost(String url, Map<String, String> params) {
        final Map<String, String> _params = params;
        iCallback = (ICallBack) f;
        String tag_string_req =   "string_req";
        StringRequest strReq = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(iCallback != null) {
                            iCallback.onRecieve(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(iCallback != null) {
                    iCallback.onError(VolleyErrorHelper.getMessage(error,f.getContext()));
                }
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                return checkParams(_params);
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void requestPost(String url, Map<String, String> params, final String auth) {
        final Map<String, String> _params = params;
        iCallback = (ICallBack) f;
        final String tag_string_req =   "string_req";
        final StringRequest strReq = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(iCallback != null) {
                            iCallback.onRecieve(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(iCallback != null) {
                    iCallback.onError(VolleyErrorHelper.getMessage(error,f.getContext()));
                }
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                return checkParams(_params);
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", auth);
                //..add other headers
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void cancelRequest(Tag tag) {
        AppController.getInstance().cancelPendingRequests(tag);
    }

    public void requestPost(String url, Map<String, String> params,final ICallback2 iCallback) {
        final Map<String, String> _params = params;
        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        iCallback.onRecieve(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallback.onRecieve(VolleyErrorHelper.getMessage(error,f.getContext()));
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return checkParams(_params);
            }
        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    //=======================================
    // used get for result at the same method
    //=======================================
    public void requestGet(String url, final ICallback2 iCallback) {

        String tag_string_req = "string_req";
        StringRequest strReq = new StringRequest(Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(iCallback != null) {
                            iCallback.onRecieve(response);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(iCallback != null) {
                    iCallback.onRecieve(error.getMessage());
                }
            }
        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                MaxTimeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
}
