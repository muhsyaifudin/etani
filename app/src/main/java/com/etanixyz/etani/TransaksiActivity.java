package com.etanixyz.etani;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransaksiActivity extends ActionBarActivity {
    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi);
        expandableListView = (ExpandableListView)findViewById(R.id.expand_listview1);
        List<String> Heading = new ArrayList<String>();
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        List<String> L3 = new ArrayList<String>();
        HashMap<String,List<String>> child_list = new HashMap<String,List<String>>();

        String heading_item[] = getResources().getStringArray(R.array.header_titles);
        String l1[] = getResources().getStringArray(R.array.h1);
        String l2[] = getResources().getStringArray(R.array.h2);
        String l3[] = getResources().getStringArray(R.array.h3);

        for (String title : heading_item)
        {
            Heading.add(title);
        }
        for (String title : l1)
        {
            L1.add(title);
        }
        for (String title : l2)
        {
            L2.add(title);
        }
        for (String title : l3)
        {
            L3.add(title);
        }
        child_list.put(Heading.get(0),L1);
        child_list.put(Heading.get(1),L2);
        child_list.put(Heading.get(2),L3);

        BackgroundTransaksi backgroundTransaksi = new BackgroundTransaksi(this,Heading,child_list);
        expandableListView.setAdapter(backgroundTransaksi);
    }
}
