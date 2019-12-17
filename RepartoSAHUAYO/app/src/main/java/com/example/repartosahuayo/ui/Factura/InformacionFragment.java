package com.example.repartosahuayo.ui.Factura;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.repartosahuayo.Adapter.Datos;
import com.example.repartosahuayo.Adapter.ListAdapterDetalles;
import com.example.repartosahuayo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformacionFragment extends Fragment {
    private ListView listView;
    private static ListAdapterDetalles adapter;
    ArrayList<Datos> items;


    public InformacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_informacion, container, false);
        Dato(rootView);
        return rootView;
    }
    private void Dato(View v){
        listView = v.findViewById(R.id.recycler);
        items = new ArrayList<>();
        items.add(new Datos("SE0312","SERVITOALLA PETALO 3 X 2 60 HOJAS 12/2 UNI.",1));
        items.add(new Datos("IN0146","INSECTICIDA RAID AEROSOL CASA Y JARDIN 12/400 MLS.",1));
        items.add(new Datos("FR0157","FRIJOLES ISADORA REFRITOS NEGROS 24/430 GRS.",1));
        adapter = new ListAdapterDetalles(items,getActivity().getApplicationContext());
        listView.setAdapter(adapter);
    }

}
