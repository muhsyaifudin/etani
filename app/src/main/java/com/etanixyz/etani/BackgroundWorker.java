package com.etanixyz.etani;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
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
 * Created by Horizon on 7/11/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {
	Context context;
	AlertDialog alertDialog;
	SessionManager sessionManager;
	String email, password;
	BackgroundWorker (Context ctx){
		context = ctx;


	}


	@Override
	protected String doInBackground(String... params) {
		String type = params[0];
		String login_url = "http://e-tani.xyz/users/login.php";
		String register_url = "http://e-tani.xyz/users/register.php";
		if (type.equals("login")){
			try {
				email = params[1];
				password = params[2];
				URL url = new URL(login_url);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				OutputStream outputStream = httpURLConnection.getOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
				String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
						URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if (type.equals("register")){
			try {
				String nama = params[1];
				String per = params[2];
				String alamat = params[3];
				String pos = params[4];
				String telepon = params[5];
				email = params[6];
				password = params[7];
				URL url = new URL(register_url);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				OutputStream outputStream = httpURLConnection.getOutputStream();
				BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
				String post_data = URLEncoder.encode("nama","UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"+
						URLEncoder.encode("per","UTF-8")+"="+URLEncoder.encode(per,"UTF-8")+"&"+
						URLEncoder.encode("alamat","UTF-8")+"="+URLEncoder.encode(alamat,"UTF-8")+"&"+
						URLEncoder.encode("pos","UTF-8")+"="+URLEncoder.encode(pos,"UTF-8")+"&"+
						URLEncoder.encode("telepon","UTF-8")+"="+URLEncoder.encode(telepon,"UTF-8")+"&"+
						URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
						URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Login Status");
	}

	@Override
	protected void onPostExecute(String result) {
		if (result.equals("success")){
			Intent i = new Intent(context, MainActivity.class);
			context.startActivity(i);
			sessionManager = new SessionManager(context);
			sessionManager.createSession(email);
		}else{
			Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		}


	}

	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
	}


}
