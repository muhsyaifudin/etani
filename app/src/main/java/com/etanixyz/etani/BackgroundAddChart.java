package com.etanixyz.etani;

import android.content.Context;
import android.os.AsyncTask;
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
 * Created by Horizon on 7/14/2017.
 */

public class BackgroundAddChart extends AsyncTask<String, Void, String> {
    SessionManager sessionManager;

    String kd_produk, email, jml;
    Context context;
    public BackgroundAddChart(Context ctx) {
        context = ctx;


    }

    @Override
    protected String doInBackground(String... params) {
        kd_produk = params[0];
        jml = params[1];
        String add_url = "http://e-tani.xyz/products/addchart.php";
        try {
            URL url = new URL(add_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                    URLEncoder.encode("kd_produk","UTF-8")+"="+URLEncoder.encode(kd_produk,"UTF-8")+"&"+
                    URLEncoder.encode("jml","UTF-8")+"="+URLEncoder.encode(jml,"UTF-8");
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

        sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.getUserDetails();
        email = user.get(SessionManager.kunci_email);

    }

    @Override
    protected void onPostExecute(String result) {

        if (result.equals("success")){
            Toast.makeText(context, "Berhasil Ditambahkan Ke Keranjang", Toast.LENGTH_SHORT).show();
        }
        else  {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
