package com.example.repartosahuayo.ui.Factura;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.repartosahuayo.Adapter.Contactos;
import com.example.repartosahuayo.Adapter.ListAdapterContactos;
import com.example.repartosahuayo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactosFragment extends Fragment {
    private ListView list;
    private static ListAdapterContactos adapter;
    ArrayList<Contactos> items;

    public ContactosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contactos, container, false);
        Contacto(rootView);
        return rootView;
    }
    private void Contacto(View v){
        list = v.findViewById(R.id.list);
        items = new ArrayList<>();
        items.add(new Contactos("Correo","ana@gmail.com"));
        items.add(new Contactos("Telefono","5590872345"));
        items.add(new Contactos("Telefono personal","5567234567"));
        adapter = new ListAdapterContactos(items,getActivity().getApplicationContext());
        list.setAdapter(adapter);
    }
}
