package com.pebblescrow.uploadimagetodatabae;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolderClass> {


    ArrayList<ModelClass> onjectModelClassList;

    public RVAdapter(ArrayList<ModelClass> onjectModelClassList) {
        this.onjectModelClassList = onjectModelClassList;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RVViewHolderClass(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.single_row,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass rvViewHolderClass, int i) {
        ModelClass objectModelClass = onjectModelClassList.get(i);
        rvViewHolderClass.imageNameTV.setText(objectModelClass.getImageName());
    }

    @Override
    public int getItemCount() {
        return onjectModelClassList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder
    {

        TextView imageNameTV;
        ImageView objectImageView;
        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTV = itemView.findViewById(R.id.sr_imageDetailsTV);
            objectImageView = itemView.findViewById(R.id.sr_imageTV);
        }
    }
}
