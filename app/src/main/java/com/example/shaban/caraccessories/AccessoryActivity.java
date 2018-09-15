package com.example.shaban.caraccessories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.shaban.caraccessories.adapters.AccessoryAdapter;
import com.example.shaban.caraccessories.model.Accessory;

import java.util.ArrayList;

public class AccessoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAccessoryActivity);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessoryActivity.super.onBackPressed();
            }
        });

        ArrayList<Accessory> accessoryList = new ArrayList<>();
        accessoryList.add(new Accessory("","This is the description of the accessory","80K",R.drawable.direc,true));
        accessoryList.add(new Accessory("BENZ GLA!","This is the description of the accessory","",R.drawable.direc2,false));
        accessoryList.add(new Accessory("","This is the description of the accessory","",R.drawable.direc3,false));

        AccessoryAdapter accessoryAdapter = new AccessoryAdapter(this,accessoryList);

        ListView listView = (ListView) findViewById(R.id.accessories_list_view);
        listView.setAdapter(accessoryAdapter);
    }

}
