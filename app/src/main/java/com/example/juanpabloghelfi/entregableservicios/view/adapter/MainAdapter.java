package com.example.juanpabloghelfi.entregableservicios.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

import java.util.List;

/**
 * Created by juanpabloghelfi on 235//18.
 */

public class MainAdapter extends RecyclerView.Adapter{

    private List<ObrasDTO> obrasDTO;

    public MainAdapter(List<ObrasDTO> obrasDTO) {
        this.obrasDTO = obrasDTO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
