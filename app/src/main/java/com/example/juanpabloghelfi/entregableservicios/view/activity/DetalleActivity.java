package com.example.juanpabloghelfi.entregableservicios.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.DetalleFragment;
import com.example.juanpabloghelfi.entregableservicios.view.fragment.MainFragment;
import android.support.v4.app.Fragment;

import static com.example.juanpabloghelfi.entregableservicios.view.fragment.DetalleFragment.OBRA_DTO;


public class DetalleActivity extends AppCompatActivity {

    static final String OBRADTO = "OBRADTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        DetalleFragment detalleFragment = new DetalleFragment();
        Bundle bundle = new Bundle();
        ObrasDTO obra =  (ObrasDTO) getIntent().getSerializableExtra(OBRA_DTO);
        bundle.putSerializable(OBRA_DTO, obra);;
        detalleFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.detalleFragment, detalleFragment).commit();
    }

}
