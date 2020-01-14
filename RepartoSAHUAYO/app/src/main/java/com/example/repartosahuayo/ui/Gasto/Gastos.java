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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class Gastos extends Fragment {
    private ImageView maniobra,casetas,flete,talacha,estacionamientos,otros;
    boolean isRotate = false;
    private static int REQUEST_CODE = 123;
    private static final String TAG = Gastos.class.getSimpleName();
    private ListView eventosli;
    private ListAdapterEventos adapter;
    ArrayList<Eventos> items;


    public Gastos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.gastos, container, false);
        eventosli = rootView.findViewById(R.id.gastoslist);
        ViewAnimation.init(rootView.findViewById(R.id.maniobras));
        ViewAnimation.init(rootView.findViewById(R.id.casetas));
        ViewAnimation.init(rootView.findViewById(R.id.fletes));
        ViewAnimation.init(rootView.findViewById(R.id.talachas));
        ViewAnimation.init(rootView.findViewById(R.id.estacionamiento));
        ViewAnimation.init(rootView.findViewById(R.id.otros_gastos));
        FloatingActionButton fab = rootView.findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = ViewAnimation.rotateFab(v, !isRotate);
                if (isRotate) {
                    ViewAnimation.showIn(rootView.findViewById(R.id.maniobras));
                    ViewAnimation.showIn(rootView.findViewById(R.id.casetas));
                    ViewAnimation.showIn(rootView.findViewById(R.id.fletes));
                    ViewAnimation.showIn(rootView.findViewById(R.id.talachas));
                    ViewAnimation.showIn(rootView.findViewById(R.id.estacionamiento));
                    ViewAnimation.showIn(rootView.findViewById(R.id.otros_gastos));
                } else {
                    ViewAnimation.showOut(rootView.findViewById(R.id.maniobras));
                    ViewAnimation.showOut(rootView.findViewById(R.id.casetas));
                    ViewAnimation.showOut(rootView.findViewById(R.id.fletes));
                    ViewAnimation.showOut(rootView.findViewById(R.id.talachas));
                    ViewAnimation.showOut(rootView.findViewById(R.id.estacionamiento));
                    ViewAnimation.showOut(rootView.findViewById(R.id.otros_gastos));
                }
            }
        });
        items = new ArrayList<>();
        Maniobras(rootView);
        Casetas(rootView);
        Fletes(rootView);
        Talachas(rootView);
        Estacionamientos(rootView);
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

    private void Maniobras(View view){
        maniobra = view.findViewById(R.id.maniobras);
        maniobra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment man = new Maniobras();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                man.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, man);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
    private void Casetas(View view){
        casetas = view.findViewById(R.id.casetas);
        casetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment cas = new CasetasFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                cas.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, cas);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void Fletes(View view){
        flete = view.findViewById(R.id.fletes);
        flete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fle = new FletesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                fle.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, fle);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void Talachas(View view){
        talacha = view.findViewById(R.id.talachas);
        talacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment tala = new TalachasFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                tala.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, tala);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void Estacionamientos(View view){
        estacionamientos = view.findViewById(R.id.estacionamiento);
        estacionamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment estacion = new EstacionamientoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                estacion.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, estacion);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void OtrosGastos(View view){
        otros = view.findViewById(R.id.otros_gastos);
        otros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment otros_gastos = new OtrosGastosFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                otros_gastos.setTargetFragment(Gastos.this,REQUEST_CODE);
                transaction.replace(R.id.nav_host_fragment, otros_gastos);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
