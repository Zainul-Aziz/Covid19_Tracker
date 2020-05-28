package com.fz.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/v2/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Log.d("myapp" , "The response is " + response.getString("cases"));
                    //Log.d("cases",cases);
                    String totalCases = response.getString("cases");
                    TextView totalCase = findViewById(R.id.totalCases);
                    totalCase.setText(totalCases);
                    String newCases = response.getString("todayCases");
                    TextView newCase = findViewById(R.id.newCases);
                    newCase.setText(newCases);
                    String totalDeaths = response.getString("deaths");
                    TextView totalDeath = findViewById(R.id.totaldeaths);
                    totalDeath.setText(totalDeaths);
                    String newDeaths = response.getString("todayDeaths");
                    TextView newDeath = findViewById(R.id.newDeaths);
                    newDeath.setText(newDeaths);
                    String recoveredCases = response.getString("recovered");
                    TextView recovered = findViewById(R.id.recoveredCases);
                    recovered.setText(recoveredCases);
                    String activeCases = response.getString("active");
                    TextView active = findViewById(R.id.activeCases);
                    active.setText(activeCases);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","Something went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
