package com.etanixyz.etani;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.widget.Toast;

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
import java.util.HashMap;

/**
 * Created by grassroot on 7/14/17.
 */

public class BackgroundCheckout extends AsyncTask<String, Void, String> {

    Context context;

    public BackgroundCheckout(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String kd_transaksi, tgl_transaksi, kd_user, status, detail_json;
        kd_transaksi = params[0];
        tgl_transaksi = params[1];
        kd_user = params[2];
        status = params[3];
        detail_json = params[4];

        String url_checkout = "http://e-tani.xyz/products/checkout.php";

        try {
            URL url = new URL(url_checkout);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("kd_transaksi","UTF-8")+"="+URLEncoder.encode(kd_transaksi,"UTF-8")+"&"+
                    URLEncoder.encode("tgl_transaksi","UTF-8")+"="+URLEncoder.encode(tgl_transaksi,"UTF-8")+"&"+
                    URLEncoder.encode("kd_user","UTF-8")+"="+URLEncoder.encode(kd_user,"UTF-8")+"&"+
                    URLEncoder.encode("status_transaksi","UTF-8")+"="+URLEncoder.encode(status,"UTF-8")+"&"+
                    URLEncoder.encode("detail_json","UTF-8")+"="+URLEncoder.encode(detail_json,"UTF-8");
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


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        Intent i = new Intent(context, CheckoutActivity.class);
        i.putExtra("data", result);
        context.startActivity(i);
    }
}
