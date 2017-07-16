package com.etanixyz.etani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;

import com.etanixyz.etani.R;

public class BantuanActivity extends ListActivity {
    String [] teman ={"Syarat dan Ketentuan", "Customer Service", "Tentang E-Tani", "Kritik dan Saran"};
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, teman));
    }
}
