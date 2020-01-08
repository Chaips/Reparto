package com.example.repartosahuayo.ui.Gasto;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.repartosahuayo.Adapter.Eventos;
import com.example.repartosahuayo.Adapter.ListAdapterEventos;
import com.example.repartosahuayo.Animation.ViewAnimation;
import com.example.repartosahuayo.R;
import com.example.repartosahuayo.ui.Evento.Combustible;
import com.example.repartosahuayo.ui.Evento.ComidasFragment;
import com.example.repartosahuayo.ui.Evento.Otros_GastosFragment;
import com.example.repartosahuayo.ui.Evento.PernoctarFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApartadoEventosFragment extends Fragment {
    private ImageView combustible,comidas,pernoctar,otro_gasto;
    boolean isRotate = false;
    private ListView eventosli;
    private ListAdapterEventos adapter;
    ArrayList<Eventos> items;
    private static int REQUEST_CODE = 123;
    private static final String TAG = ApartadoEventosFragment.class.getSimpleName();

    public ApartadoEventosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_apartado_eventos, container, false);
        eventosli = rootView.findViewById(R.id.eventoslist);
        ViewAnimation.init(rootView.findViewById(R.id.combustible));
        ViewAnimation.init(rootView.findViewById(R.id.comidas));
        ViewAnimation.init(rootView.findViewById(R.id.pernoctar));
        ViewAnimation.init(rootView.findViewById(R.id.otros));
        FloatingActionButton fab = rootView.findViewById(R.id.fabAdd);
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
        items = new ArrayList<>();
        Combustibles(rootView);
        Comida(rootView);
        Pernoctar(rootView);
        OtrosGastos(rootView);
        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){ ;
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            double importe = data.getDoubleExtra("importes",0);
            String file = data.getStringExtra("folio");
            String event = data.getStringExtra("eventos");
            items.add(new Eventos(event,file,importe));
            items.add(new Eventos("fedf","saz",12.42));
            adapter = new ListAdapterEventos(items,getActivity());
            eventosli.setAdapter(adapter);
            Toast.makeText(getActivity()," "+importe+" "+file+ " "+event,Toast.LENGTH_LONG).show();
            Timber.d(TAG,"OnActivityResult: "+importe);
        }
    }

    private void Combustibles(View view){
        combustible = view.findViewById(R.id.combustible);
        combustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment conta = new Combustible();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                conta.setTargetFragment(ApartadoEventosFragment.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, conta);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void Comida(View view){
        comidas = view.findViewById(R.id.comidas);
        comidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment com = new ComidasFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                com.setTargetFragment(ApartadoEventosFragment.this, REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, com);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void Pernoctar(View view){
        pernoctar = view.findViewById(R.id.pernoctar);
        pernoctar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment per = new PernoctarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                per.setTargetFragment(ApartadoEventosFragment.this, REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, per);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void OtrosGastos(View view){
        otro_gasto = view.findViewById(R.id.otros);
        otro_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment otros =new Otros_GastosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                otros.setTargetFragment(ApartadoEventosFragment.this, REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, otros);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
