package com.example.shaban.caraccessories;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.shaban.caraccessories.adapters.CategoryAdapter;
import com.example.shaban.caraccessories.model.Category;
import com.google.android.gms.internal.n;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

        ImageView profileBtn = (ImageView)findViewById(R.id.profile_icon);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this,ProfileActivity.class));
            }
        });

        final ArrayList<Category> list = new ArrayList<>();

		list.add(new Category("MERCEDES", R.drawable.merceds));
        list.add(new Category("BMW", R.drawable.bmw));

        CategoryAdapter cAdapter = new CategoryAdapter(this, list);
        ListView listView = (ListView)findViewById(R.id.category_list);
        listView.setAdapter(cAdapter);
    }
}
