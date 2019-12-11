package com.example.repartosahuayo.ui.Gasto;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.repartosahuayo.Adapter.Contactos;
import com.example.repartosahuayo.Adapter.Datos;
import com.example.repartosahuayo.Adapter.Eventos;
import com.example.repartosahuayo.Adapter.ListAdapterContactos;
import com.example.repartosahuayo.Adapter.ListAdapterDetalles;
import com.example.repartosahuayo.Adapter.ListAdapterEventos;
import com.example.repartosahuayo.Animation.ViewAnimation;
import com.example.repartosahuayo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartadoEventosFragment extends Fragment {
    private ImageView combustible;
    boolean isRotate = false;
    private ListView eventosli;
    private static ListAdapterEventos adapter;
    ArrayList<Eventos> items;

    public ApartadoEventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_apartado_eventos, container, false);
        Combustibles(rootView);
        ViewAnimation.init(rootView.findViewById(R.id.combustible));
        ViewAnimation.init(rootView.findViewById(R.id.comidas));
        ViewAnimation.init(rootView.findViewById(R.id.pernoctar));
        ViewAnimation.init(rootView.findViewById(R.id.otros));
        FloatingActionButton fab = rootView.findViewById(R.id.fabAdd);
        eventosli = rootView.findViewById(R.id.eventoslist);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = ViewAnimation.rotateFab(v, !isRotate);
                if (isRotate) {
                    ViewAnimation.showIn(rootView.findViewById(R.id.combustible));
                    ViewAnimation.showIn(rootView.findViewById(R.id.comidas));
                    ViewAnimation.showIn(rootView.findViewById(R.id.pernoctar));
                    ViewAnimation.showIn(rootView.findViewById(R.id.otros));
                } else {
                    ViewAnimation.showOut(rootView.findViewById(R.id.combustible));
                    ViewAnimation.showOut(rootView.findViewById(R.id.comidas));
                    ViewAnimation.showOut(rootView.findViewById(R.id.pernoctar));
                    ViewAnimation.showOut(rootView.findViewById(R.id.otros));
                }
            }
        });
       /* Bundle b=getArguments();
        items = new ArrayList<>();
        double importes = b.getDouble("importes");
        String folio = b.getString("folio");
        String eventos = b.getString("eventos");
        items.add(new Eventos(eventos,folio,importes));
        adapter = new ListAdapterEventos(items,getActivity().getApplicationContext());
        eventosli.setAdapter(adapter);*/
        return rootView;
    }

    public void Combustibles(View view){
        combustible = view.findViewById(R.id.combustible);
        combustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment conta = new Combustible();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, conta);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
