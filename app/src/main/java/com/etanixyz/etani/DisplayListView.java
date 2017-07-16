package com.etanixyz.etani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;

    JSONObject jsonObject;
    JSONArray jsonArray;

    ProductAdapter productAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view);
        json_string = getIntent().getExtras().getString("data");


        productAdapter = new ProductAdapter(this, R.layout.row_layout);
        listView =(ListView)findViewById(R.id.listview);
        listView.setAdapter(productAdapter);

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server response");
            int count=0;
            String nama_produk, foto_produk, harga_produk,min, pupuk, kd_produk, nama_petani, alamat, telepon;
            while (count<jsonObject.getJSONArray("server response").length()){
                String lg = String.valueOf(jsonObject.getJSONArray("server response").length());
//               Toast.makeText(DisplayListView.this, json_string, Toast.LENGTH_LONG).show();
                JSONObject JO = jsonArray.getJSONObject(count);

                nama_produk=JO.getString("nama_produk");
                harga_produk = JO.getString("harga");
                min = JO.getString("minimal_beli");
                pupuk= JO.getString("pupuk");
                kd_produk= JO.getString("kd_produk");
                nama_petani=JO.getString("nama_petani");
                alamat=JO.getString("alamat_petani");
                telepon=JO.getString("no_tlp_petani");
                foto_produk=JO.getString("foto_produk");

//              Toast.makeText(DisplayListView.this, lg, Toast.LENGTH_LONG).show();
                Product product = new Product(kd_produk,pupuk, nama_produk, harga_produk, min, nama_petani, telepon, alamat, foto_produk);
                productAdapter.add(product);


                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showChart(View view){

        Intent i = new Intent(getApplicationContext(), KeranjangActivity.class);
        startActivity(i);

    }
    public void back(View view){
        finish();
    }


}
