package com.example.juanpabloghelfi.entregableservicios.view.fragment;

import android.content.Context;
import android.os.Bundle;

import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.ResultListener;
import com.example.juanpabloghelfi.entregableservicios.controller.ObrasController;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.example.juanpabloghelfi.entregableservicios.view.adapter.MainAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    List<ObrasDTO> listObras;

    RecyclerView recyclerView;


    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        this.recyclerView = view.findViewById(R.id.recyclerMain);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        this.loadObras();

        return view;
    }

    private void loadObras() {
        ObrasController obrasController = new ObrasController();
        obrasController.getObras(new ResultListener<List<ObrasDTO>>() {
            @Override
            public void finish(List<ObrasDTO> resultado) {

                MainAdapter adapter = new MainAdapter(resultado);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}