package com.example.juanpabloghelfi.entregableservicios.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juanpabloghelfi.entregableservicios.R;
import com.example.juanpabloghelfi.entregableservicios.dto.ObrasDTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {

    ObrasDTO obrasDTO;
    TextView nombre;
    public static final String OBRA_DTO = "OBRA_DTO" ;


    public DetalleFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        this.nombre = view.findViewById(R.id.nombre_obra);

        obrasDTO = (ObrasDTO) getArguments().getSerializable(OBRA_DTO);


        this.nombre.setText(obrasDTO.getName());

        return view;
    }

}
