package com.etanixyz.etani;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;

import com.etanixyz.etani.R;

public class RiwayatActivity extends ListActivity {
    String [] berita={"Jumat, 14 Juli 2017   TR00323   Belum Dikonfirmasi","Kamis, 13 Juli 2017   TR00320   Sudah Dikonfirmasi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, berita));
    }
}
