package com.etanixyz.etani;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etanixyz.etani.R;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class AkunActivity extends Activity {
    SessionManager sessionManager;
    TextView namaTx, perTx, emailTx, teleponTx, alamatTx, posTx;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun);

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email = user.get(SessionManager.kunci_email);

//        Toast.makeText(getApplicationContext(),"email "+email, Toast.LENGTH_LONG).show();

        namaTx = (TextView)findViewById(R.id.nama);
        perTx = (TextView)findViewById(R.id.perusahaan);
        emailTx = (TextView)findViewById(R.id.email);
        teleponTx = (TextView)findViewById(R.id.no_telp);
        alamatTx = (TextView)findViewById(R.id.alamat);
        posTx = (TextView)findViewById(R.id.kodepos);


        BackgroundViewAkun backgroundViewAkun = new BackgroundViewAkun(getApplicationContext());
        backgroundViewAkun.execute();



    }
    public void processvalue(String result){
        //Toast.makeText(getApplicationContext(),"json "+result, Toast.LENGTH_LONG).show();
        json_string = result;

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server response");
            int count=0;
            while (count<jsonObject.getJSONArray("server response").length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                namaTx.setText(JO.getString("nama_user"));
                perTx.setText(JO.getString("nama_perusahaan"));
                emailTx.setText(JO.getString("email_user"));
                teleponTx.setText(JO.getString("no_tlp_user"));
                alamatTx.setText(JO.getString("alamat_user"));
                posTx.setText(JO.getString("kode_pos"));
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void logout(View v){
        sessionManager.logout();
    }
    private class BackgroundViewAkun extends AsyncTask<Void, Void, String> {
        String tampil_url, json_string,email;

        SessionManager sessionManager;
        Context context;

        public BackgroundViewAkun(Context ctx) {
            context = ctx;
            sessionManager = new SessionManager(context);

        }

        @Override
        protected void onPreExecute() {
            HashMap<String, String> user = sessionManager.getUserDetails();
            email = user.get(SessionManager.kunci_email);
        }

        @Override
        protected void onPostExecute(String result) {
            processvalue(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... params) {
            String result="";
            tampil_url= "http://e-tani.xyz/users/tampil-akun.php";
            try {
                URL url = new URL(tampil_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));



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

    }
}
