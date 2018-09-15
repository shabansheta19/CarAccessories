package com.example.shaban.caraccessories.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaban.caraccessories.R;
import com.example.shaban.caraccessories.model.Accessory;

import java.util.ArrayList;

public class AccessoryAdapter extends ArrayAdapter<Accessory> {

    public AccessoryAdapter(Activity context, ArrayList<Accessory> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.accessory_list_item, parent, false);
        }

        final Accessory curAccessory = getItem(position);

        ImageView img = (ImageView)listViewItem.findViewById(R.id.accessor_img);
        img.setImageResource(curAccessory.getImgResourceId());

        ImageView favIcon = (ImageView)listViewItem.findViewById(R.id.favorite_icon);
        if (curAccessory.isFavorite())
            favIcon.setImageResource(R.drawable.favorit);
        else
            favIcon.setImageResource(R.drawable.add_to_favorit);

        TextView priceView = (TextView)listViewItem.findViewById(R.id.accessory_price);
        if (curAccessory.getPrice().equals("-1"))
            priceView.setVisibility(View.GONE);
        else
            priceView.setText(curAccessory.getPrice());

        TextView titleView = (TextView)listViewItem.findViewById(R.id.accessory_title);
        if (curAccessory.getTitle() == null) {
            titleView.setVisibility(View.GONE);
        }
        else
            titleView.setText(curAccessory.getTitle());

        return listViewItem;

    }
}
