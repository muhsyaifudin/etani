package com.etanixyz.etani;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Horizon on 7/12/2017.
 */

public class Backgroundviewproducts extends AsyncTask<Void, Void, String> {
    String tampil_url, json_string, jenis;
    Context context;

    Backgroundviewproducts(Context ctx, String jenis){

        context=ctx;
        this.jenis=jenis;
    }

    @Override
    protected void onPreExecute() {
        tampil_url= "http://e-tani.xyz/products/tampil-produk.php";
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(tampil_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("jenis","UTF-8")+"="+URLEncoder.encode(jenis,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result="";

            while ((json_string = bufferedReader.readLine()) != null){
               result += json_string;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Intent i = new Intent(context, DisplayListView.class);
        i.putExtra("data", result);
        context.startActivity(i);
    }
}
