package com.example.sportsleague.ui.Pages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sportsleague.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class LeadboardJSon extends AppCompatActivity {

    public static final String TAG = "HELP";
    private TextView result;
    private Button btnGet, btnPost;
    private RequestQueue mQueue;
    String URL, name;

    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_leaderboard);


        //Get username
        name = getIntent().getStringExtra("username");

        // Setup UI
        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);
        result = (TextView) findViewById(R.id.result);

        btnGet.setOnClickListener((View.OnClickListener) this);
        btnPost.setOnClickListener((View.OnClickListener) this);


        //Instantiate request queue to send requests
        mQueue = Volley.newRequestQueue(this);

        //Set base url, makes it easier to change urls later
        //Stored my url in strings.xml(values/strings.xml)
        URL = getResources().getString(R.string.remote);
    }

    /**
     * Volley String Get Request Example
     * @param url
     */
    public void httpGetTest(View view){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // TODO SERVER RESPONSE HANDLING
                Log.d(TAG, "SERVER RESPONSE: " + response);

                //Print to a textview or do whatever you want with it
                result.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO ERROR HANDLING
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    Log.d(TAG, "SERVER ERROR RESPONSE: " + responseBody);
                    result.setText("error" + responseBody);

                } catch (UnsupportedEncodingException e){
                    Log.d(TAG,e.toString());
                }
            }
        }){
            // THIS METHOD IS NOT CALLED ON A GET REQUEST! ONLY CALLED DURING POST OR PUT!
            // THIS METHOD IS MEANINGLESS
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<>(); // Can also pass in hashmap through method, etc.
                //TODO Add parameters to post request here
                //Example:
                params.put("username", name); //Key-Value pair that is very similar to how PostMan works
                return params;
            }
        };
        mQueue.add(stringRequest);
    }

    /**
     * Volley String Post Request with RequestBody Example
     */
    public void httpPostTest2(View view) throws JSONException {
        //Convert passed in data into request body
        //JSONObject is VERY similar to hashmap formatting
        EditText user = (EditText) findViewById(R.id.username);
        String username = user.getText().toString();
        String url = getResources().getString(R.string.remote) + "/add";
        JSONObject data = new JSONObject();
        data.put("name", username);
        final String requestBody = data.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);

                //Print to a textview or do whatever you want with it
                result.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {

                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
        };

        //Add request to queue
        mQueue.add(stringRequest);
    }
}
