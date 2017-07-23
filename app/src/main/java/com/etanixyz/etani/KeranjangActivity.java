package com.etanixyz.etani;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class KeranjangActivity extends AppCompatActivity {
    String json_string, kd_user;
    JSONObject jsonObject;
    JSONArray jsonArray;

    KeranjangAdapter keranjangAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keranjang);

        BackgroundViewChart backgroundViewChart = new BackgroundViewChart();
        backgroundViewChart.execute();

    }

    public void back(View view){
        finish();
    }
    public void parseJson(String json_str){

        json_string = json_str;
        keranjangAdapter =  new KeranjangAdapter(this, R.layout.row_keranjang);
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(keranjangAdapter);

        try {
//            jsonObject = new JSONObject(json_string);
//            jsonArray = jsonObject.getJSONArray("server response");
            jsonArray = new JSONArray(json_string);
            int count = 0;

            String kd_keranjang, jml, kd_produk, pupuk, nama_produk, harga_produk, nama_petani, foto_produk;
            while (count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);
                kd_keranjang=JO.getString("kd_keranjang");
                jml=JO.getString("jml");
                kd_produk=JO.getString("kd_produk");
                nama_petani=JO.getString("nama_petani");
                pupuk=JO.getString("pupuk");
                foto_produk=JO.getString("foto_produk");
                nama_produk=JO.getString("nama_produk");
                harga_produk = JO.getString("harga");
                kd_user = JO.getString("kd_user");


                Keranjang keranjang = new Keranjang(kd_keranjang, jml, kd_produk, pupuk, nama_produk, harga_produk, nama_petani, foto_produk);
                keranjangAdapter.add(keranjang);


                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void checkout(View view){
        Intent i = new Intent(getApplicationContext(),TransaksiActivity.class);
        startActivity(i);
//        String tanggal, kode_transaksi, status;
//        BackgroundCheckout backgroundCheckout = new BackgroundCheckout(this);
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat simpledateformat2 = new SimpleDateFormat("ddHHmmss");
//        tanggal = simpledateformat.format(calendar.getTime());
//        kode_transaksi = simpledateformat2.format(calendar.getTime());
//        status = "Belum Dibayar";
//
//        backgroundCheckout.execute(kode_transaksi, tanggal, kd_user, status, json_string);




    }

    private class BackgroundViewChart extends AsyncTask<Void, Void, String> {
        SessionManager sessionManager;

        String email;

        public BackgroundViewChart() {

        }

        @Override
        protected String doInBackground(Void... params) {
            String view_url="http://e-tani.xyz/products/tampil-keranjang.php";

            try {
                URL url = new URL(view_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    result +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            sessionManager = new SessionManager(getApplicationContext());

            HashMap<String, String> user = sessionManager.getUserDetails();
            email = user.get(SessionManager.kunci_email);
        }

        @Override
        protected void onPostExecute(String result) {
            parseJson(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
