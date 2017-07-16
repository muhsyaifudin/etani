package com.etanixyz.etani;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.etanixyz.etani.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
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

public class ProdukActivity extends Activity {
    ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produk);
        iv1 = (ImageView)findViewById(R.id.btnbawangmerah);
        iv2 = (ImageView)findViewById(R.id.btnbawangputih);
        iv3 = (ImageView)findViewById(R.id.btnbayam);
        iv4 = (ImageView)findViewById(R.id.btnkentang);
        iv5 = (ImageView)findViewById(R.id.btnkubis);
        iv6 = (ImageView)findViewById(R.id.btncabai);
        iv7 = (ImageView)findViewById(R.id.btnwortel);
        iv8 = (ImageView)findViewById(R.id.btnsawi);
        iv9 = (ImageView)findViewById(R.id.btnterong);
        iv10 = (ImageView)findViewById(R.id.btnselada);
        iv11 = (ImageView)findViewById(R.id.btntomat);
        iv12 =(ImageView)findViewById(R.id.btnkangkung);

        Picasso.with(this).load("http://e-tani.xyz/products/jenis/bawangmerah.png").into(iv1);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/bawangputih.png").into(iv2);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/bayam.png").into(iv3);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/kentang.png").into(iv4);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/kubis.png").into(iv5);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/cabai.png").into(iv6);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/wortel.png").into(iv7);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/sawi.png").into(iv8);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/terong.png").into(iv9);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/selada.png").into(iv10);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/tomat.png").into(iv11);
        Picasso.with(this).load("http://e-tani.xyz/products/jenis/kangkung.png").into(iv12);
    }

    public void showlist(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Bawang Merah");
        backgroundviewproducts.execute();
   }
    public void showlist2(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Bawang Putih");
        backgroundviewproducts.execute();
    }
    public void showlist3(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Bayam");
        backgroundviewproducts.execute();
    }
    public void showlist4(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Kentang");
        backgroundviewproducts.execute();
    }
    public void showlist5(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Kubis");
        backgroundviewproducts.execute();
    }
    public void showlist6(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Cabai");
        backgroundviewproducts.execute();
    }
    public void showlist7(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Wortel");
        backgroundviewproducts.execute();
    }
    public void showlist8(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Sawi");
        backgroundviewproducts.execute();
    }
    public void showlist9(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Terong");
        backgroundviewproducts.execute();
    }
    public void showlist10(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Selada");
        backgroundviewproducts.execute();
    }
    public void showlist11(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Tomat");
        backgroundviewproducts.execute();
    }
    public void showlist12(View view) {
        Backgroundviewproducts backgroundviewproducts = new Backgroundviewproducts(this, "Kangkung");
        backgroundviewproducts.execute();
    }


}
