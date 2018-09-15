package com.example.shaban.caraccessories;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.shaban.caraccessories.adapters.AccessoryInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class AccessoryInfoActivity extends AppCompatActivity {

    private FloatingActionButton callFab;
    private FloatingActionButton msgFab;
    private FloatingActionButton mapFab;
    private FloatingActionButton onlinePaymentFab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessoryInfoActivity.super.onBackPressed();
            }
        });

        List<String> list = new ArrayList();
        list.add("https://4.imimg.com/data4/FU/GC/MY-5938506/car-steering-500x500.jpg");
        list.add("https://4.imimg.com/data4/PW/WY/MY-5938506/1-500x500.jpg");
        list.add("https://4.imimg.com/data4/PA/JN/MY-5938506/car-headlight-500x500.jpg");
        list.add("https://4.imimg.com/data4/ID/HH/MY-5938506/car-lcd-monitor-500x500.jpg");
        list.add("https://4.imimg.com/data4/SO/JI/MY-5938506/car-mirror-500x500.gif");

        AccessoryInfoAdapter accessoryInfoAdapter = new AccessoryInfoAdapter(list);

        recyclerView = (RecyclerView) findViewById(R.id.accessory_info_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(accessoryInfoAdapter);

        callFab = (FloatingActionButton) findViewById(R.id.call);
        msgFab = (FloatingActionButton) findViewById(R.id.msg);
        mapFab = (FloatingActionButton) findViewById(R.id.map);
        onlinePaymentFab = (FloatingActionButton) findViewById(R.id.online_payment);

        mapFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessoryInfoActivity.this,MainActivity.class));
            }
        });

        msgFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        callFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        onlinePaymentFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

}
