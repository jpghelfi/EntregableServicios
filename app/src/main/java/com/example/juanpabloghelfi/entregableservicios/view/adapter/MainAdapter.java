package com.example.juanpabloghelfi.entregableservicios.view.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.controller.ObrasController;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class MainAdapter extends RecyclerView.Adapter{

    private List<ObrasDTO> obrasDTO;
    private Context context;

    public MainAdapter(Context context, List<ObrasDTO> obrasDTO) {
        this.obrasDTO = obrasDTO;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        public ObrasViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.image_view_id);
        }

        public void setImageView(ObrasDTO obra){
            Glide.with(context)
                    .load(obra.getImage())
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return this.obrasDTO.size();
    }
}
