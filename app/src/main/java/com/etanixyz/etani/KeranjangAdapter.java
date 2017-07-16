package com.etanixyz.etani;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horizon on 7/14/2017.
 */

public class KeranjangAdapter extends ArrayAdapter {
    List list = new ArrayList();
    Context context;

    public KeranjangAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        final Keranjangholder keranjangholder;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_keranjang, parent, false);

            keranjangholder = new Keranjangholder();

            keranjangholder.nama_produk = (TextView)row.findViewById(R.id.nama_produk);
            keranjangholder.harga_produk = (TextView)row.findViewById(R.id.harga);
            keranjangholder.jml = (EditText)row.findViewById(R.id.jml);
            keranjangholder.nama_petani = (TextView)row.findViewById(R.id.petani);
            keranjangholder.subtotal = (TextView)row.findViewById(R.id.subtotal);
            keranjangholder.pupuk = (TextView)row.findViewById(R.id.pupuk);
            keranjangholder.foto_produk = (ImageView)row.findViewById(R.id.foto_produk);
            keranjangholder.batal = (Button)row.findViewById(R.id.btnbatal);
            row.setTag(keranjangholder);


        }
        else {
            keranjangholder = (Keranjangholder)row.getTag();
        }

        final Keranjang keranjang = (Keranjang)this.getItem(position);
        keranjangholder.nama_produk.setText(keranjang.getNama_produk());
        keranjangholder.harga_produk.setText(keranjang.getHarga_produk());
        keranjangholder.jml.setText(keranjang.getJml());
        keranjangholder.nama_petani.setText(keranjang.getNama_petani());
        keranjangholder.subtotal.setText("Subtotal = Rp "+keranjang.getSubtotal());
        keranjangholder.pupuk.setText(keranjang.getPupuk());
        String img = "http://e-tani.xyz/products/images/"+keranjang.getFoto_produk();
        Picasso.with(this.context).load(img).into(keranjangholder.foto_produk);

        keranjangholder.batal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                removechart(keranjang.getKd_keranjang());
            }
        });


        return row;
    }

    static class Keranjangholder{
       TextView subtotal,kd_produk, pupuk, nama_produk, harga_produk, nama_petani;
       ImageView foto_produk;
        EditText jml;
       Button batal;
    }

    public void removechart(String kd_keranjang){
        BackgroundRemoveChart backgroundRemoveChart = new BackgroundRemoveChart(context);
        backgroundRemoveChart.execute(kd_keranjang);



    }
}
