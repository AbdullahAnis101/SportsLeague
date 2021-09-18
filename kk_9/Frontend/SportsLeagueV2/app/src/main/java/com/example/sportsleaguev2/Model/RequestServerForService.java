package com.example.sportsleaguev2.Model;

import android.content.Context;
import android.telecom.CallRedirectionService;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RequestServerForService {
    public static final String TAG = "HELP";
    IVolleyListener myListener;
    Context context;
    protected String users;

    public RequestServerForService(Context c, IVolleyListener l) {
        this.context = c;
        this.myListener = l;
    }

    /**
     * used for adding a user to the server
     * @param username
     * @param pass
     * @param first_name
     * @param last_name
     * @param phone_number
     * @param rank
     * @throws JSONException
     */
    public void signUp(String username, String pass, String first_name, String last_name, String phone_number, int rank) throws JSONException {
        String url = "http://coms-309-kk-09.cs.iastate.edu:8080/user/add";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("userid", 12);
        jsonBody.put("username", username);
        jsonBody.put("password", pass);
        jsonBody.put("firstname", first_name);
        jsonBody.put("lastname", last_name);
        jsonBody.put("leaderboard", rank);
        jsonBody.put("phone", phone_number);
        callServerPost(jsonBody, url);
    }

    public void add_prediction(int predictionid, int date, String sport, String teams, String predictionForGame, String username, String savename) throws JSONException {
        String url = "http://coms-309-kk-09.cs.iastate.edu:8080/user/add";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("predictionid", predictionid);
        jsonBody.put("date", date);
        jsonBody.put("sport", sport);
        jsonBody.put("teams", teams);
        jsonBody.put("predictionForGame", predictionForGame);
        jsonBody.put("username", username);
        jsonBody.put("savename", savename);
        callServerPost(jsonBody, url);
    }

    /**
     * gets a list of users for our app
     * @throws JSONException
     */
    public void pullUsers() throws JSONException {
        String url = "http://coms-309-kk-09.cs.iastate.edu:8080/user";
        callServerGet(url, "Users");
    }

    public void pullSchedule() {
        String url = "http://coms-309-kk-09.cs.iastate.edu:8080/schedule/get";
        callServerGet(url, "Games");
    }

    public void pullPredictions(String user) {
        String url = "http://coms-309-kk-09.cs.iastate.edu:8080/prediction/get";
        callServerGet(url, "Predictions");
    }
    /**
     * used for POSTS to server
     * @param jazy
     * @throws JSONException
     */
    private void callServerPost(JSONObject jazy, String url) throws JSONException {

        // Must be declared final to be used in inner class
        final String requestBody = jazy.toString();

        // String post request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myListener.onSuccess(response, "");
                Log.d("VOLLEY", "SERVER RESPONSE: " + response);
//                    JSONArray res = new JSONArray(response);
                Log.i("VOLLEY", response);
                Log.d("JSONGET", response);
//                    myListener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if(error.networkResponse==null) return;
                    String responseBody = new String(error.networkResponse.data, "utf-8");
//                      Log.d(TAG, responseBody);
                    myListener.onError(responseBody);
                } catch (UnsupportedEncodingException e){
                    //Log.d(TAG,e.toString());
                }
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
//                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }

    /**
     * Volley String Get Request for Users from Server
     * @param url
     */
    public void callServerGet(String url, final String key){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEY", "SERVER RESPONSE: " + response);
                try {
                    JSONArray res = new JSONArray(response);
                    myListener.onSuccess(response, key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO ERROR HANDLING
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    Log.d(TAG, "SERVER ERROR RESPONSE: " + responseBody);
                    myListener.onError(responseBody);
//                    result.setText("error" + responseBody);

                } catch (UnsupportedEncodingException e){
                    Log.d(TAG,e.toString());
                }
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
}
