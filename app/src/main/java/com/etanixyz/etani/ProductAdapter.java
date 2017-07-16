package com.etanixyz.etani;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horizon on 7/13/2017.
 */

public class ProductAdapter extends ArrayAdapter {
    List list = new ArrayList();
    Context context;


    public ProductAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
    }


    public void add(Product object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        final ProductHolder productHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);

            productHolder = new ProductHolder();
            productHolder.nama_produk = (TextView)row.findViewById(R.id.nama_produk);
            productHolder.pupuk = (TextView)row.findViewById(R.id.pupuk);
            productHolder.harga_produk = (TextView)row.findViewById(R.id.harga);
            productHolder.min = (TextView)row.findViewById(R.id.minimal);
            productHolder.nama_petani = (TextView)row.findViewById(R.id.petani);
            productHolder.alamat = (TextView)row.findViewById(R.id.alamat);
            productHolder.telepon = (TextView)row.findViewById(R.id.notelp);
            productHolder.foto_produk =(ImageView)row.findViewById(R.id.foto_produk);
            productHolder.kd_produk=(TextView)row.findViewById(R.id.kd_produk);
            productHolder.add = (Button)row.findViewById(R.id.btnkeranjang);
            row.setTag(productHolder);

        }
        else {
            productHolder = (ProductHolder)row.getTag();
        }

        final Product product = (Product)this.getItem(position);
        productHolder.pupuk.setText(product.getPupuk());
        productHolder.nama_produk.setText(product.getNama_produk());
        productHolder.harga_produk.setText("Rp "+product.getHarga_produk()+"/Kg");
        productHolder.min.setText("Minimal Pembelian "+product.getMin()+" Kg");
        productHolder.nama_petani.setText(product.getNama_petani());
        productHolder.alamat.setText(product.getAlamat());
        productHolder.telepon.setText(product.getTelepon());
        String img = "http://e-tani.xyz/products/images/"+product.getFoto_produk();
        Picasso.with(this.context).load(img).into(productHolder.foto_produk);
        productHolder.kd_produk.setText(product.getKd_produk());

        productHolder.add.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               productHolder.add.setText("Lihat Keranjang");
               productHolder.add.setBackgroundResource(R.drawable.buttonlogin);
               addToChart(product.getKd_produk(), product.getMin());
//               Toast.makeText(context, product.getKd_produk(), Toast.LENGTH_LONG).show();
           }
        });

        return row;
    }

    static class ProductHolder{
        TextView nama_produk, harga_produk,min, pupuk, kd_produk, nama_petani, alamat, telepon;
        ImageView foto_produk;
        Button add;

    }

    public void addToChart(String kd_produk, String jml){

        BackgroundAddChart backgroundAddChart = new BackgroundAddChart(context);
        backgroundAddChart.execute(kd_produk, jml);
    }

}
