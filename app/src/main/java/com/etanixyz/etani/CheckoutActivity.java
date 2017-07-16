package com.etanixyz.etani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutActivity extends AppCompatActivity {
    String json_string;

    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        json_string = getIntent().getExtras().getString("data");

        try {
            jsonArray = new JSONArray(json_string);
            int count=0;
            String kd_transaksi, tgl_transaksi, kd_user, status;
            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);

                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
