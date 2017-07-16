package com.etanixyz.etani;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TabHost;

import com.etanixyz.etani.R;
import com.squareup.picasso.Picasso;


@SuppressWarnings("deprecation")
    public class MainActivity extends TabActivity{

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            TabHost tabhost = getTabHost();
            TabHost.TabSpec spec;
            Intent intent;

            intent = new Intent().setClass(this, ProdukActivity.class);//content pada tab yang akan kita buat
            spec = tabhost.newTabSpec("produk").setIndicator("Produk",null).setContent(intent);//mengeset nama tab dan mengisi content pada menu tab anda.
            tabhost.addTab(spec);//untuk membuat tabbaru disini bisa diatur sesuai keinginan anda

            intent = new Intent().setClass(this, RiwayatActivity.class);
            spec = tabhost.newTabSpec("riwayat").setIndicator("Riwayat",null).setContent(intent);
            tabhost.addTab(spec);

            intent = new Intent().setClass(this, BantuanActivity.class);
            spec = tabhost.newTabSpec("bantuan").setIndicator("Bantuan",null).setContent(intent);
            tabhost.addTab(spec);


            intent = new Intent().setClass(this, AkunActivity.class);
            spec = tabhost.newTabSpec("akun").setIndicator("Akun",null).setContent(intent);
            tabhost.addTab(spec);



        }
    }

