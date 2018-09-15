package com.example.shaban.caraccessories.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shaban.caraccessories.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by shaban on 9/13/2018.
 */

public class AccessoryInfoAdapter extends RecyclerView.Adapter<AccessoryInfoAdapter.MyViewHolder> {

    private List<String> imagesUri;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.accessory_info_row_image);
        }
    }

    public AccessoryInfoAdapter(List<String> imagesUri) {
        this.imagesUri = imagesUri;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accessory_info_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*URL url = null;
        try {
            url = new URL(imagesUri.get(position));
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imageView.setImageBitmap(bmp);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }*/
        if (position == 0)
            holder.imageView.setImageResource(R.drawable.c1);
        else if (position == 1)
            holder.imageView.setImageResource(R.drawable.c2);
        else if (position == 2)
            holder.imageView.setImageResource(R.drawable.c3);
        else
            holder.imageView.setImageResource(R.drawable.c4);
    }

    @Override
    public int getItemCount() {
        return imagesUri.size();
    }
}