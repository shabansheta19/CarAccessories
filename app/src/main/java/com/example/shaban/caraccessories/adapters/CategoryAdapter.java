package com.example.shaban.caraccessories.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaban.caraccessories.AccessoryActivity;
import com.example.shaban.caraccessories.R;
import com.example.shaban.caraccessories.model.Accessory;
import com.example.shaban.caraccessories.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<Category> {

    Activity activity;

    public CategoryAdapter(Activity context, ArrayList<Category> categoryList) {
        super(context, 0, categoryList);
        activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.category_list_item, parent, false);
        }

        final Category curCategory = getItem(position);


        ImageView img = (ImageView)listViewItem.findViewById(R.id.category_logo);
        if (!curCategory.hasLogo())
            img.setVisibility(View.GONE);
        else
            img.setImageResource(curCategory.getImgResourceId());

        TextView titleView = (TextView)listViewItem.findViewById(R.id.category_title);
        titleView.setText(curCategory.getTitle());

        listViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, AccessoryActivity.class));
            }
        });

        return listViewItem;
    }
}
