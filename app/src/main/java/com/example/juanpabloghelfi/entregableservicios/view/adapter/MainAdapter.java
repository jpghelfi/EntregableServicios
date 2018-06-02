package com.example.juanpabloghelfi.entregableservicios.view.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.controller.ObrasController;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

import com.example.juanpabloghelfi.entregableservicios.view.GlideApp;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class MainAdapter extends RecyclerView.Adapter{

    private List<ObrasDTO> obrasDTO;
    private Context context;

    public MainAdapter(List<ObrasDTO> obrasDTO) {
        this.obrasDTO = obrasDTO;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylcer_cell, parent, false);
        ObrasViewHolder obrasViewHolder = new ObrasViewHolder(view);
        return obrasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ObrasDTO obra = this.obrasDTO.get(position);
        ObrasViewHolder obrasViewHolder = (ObrasViewHolder) holder;
        obrasViewHolder.setImageView(obra);
    }

    private class ObrasViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ObrasViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_id);
            textView = itemView.findViewById(R.id.nombre_pintura);
        }

        public void setImageView(ObrasDTO obra){

            Glide.with(context).using(new FirebaseImageLoader())
                    .load(FirebaseStorage.getInstance().getReference().child(obra.getImage()))
                    .into(imageView);

//            GlideApp.with(context).load(FirebaseStorage.getInstance().getReference().child(obra.getImage()))
                    


            textView.setText(obra.getName());
        }
    }

    @Override
    public int getItemCount() {
        return this.obrasDTO.size();
    }
}
