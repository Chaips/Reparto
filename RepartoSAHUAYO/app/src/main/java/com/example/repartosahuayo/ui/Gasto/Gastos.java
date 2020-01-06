package com.example.repartosahuayo.ui.Gasto;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.repartosahuayo.Animation.ViewAnimation;
import com.example.repartosahuayo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class Gastos extends Fragment {
    private ImageView maniobra;
    boolean isRotate = false;


    public Gastos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.gastos, container, false);
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
        Maniobras(rootView);
        return rootView;
    }

    public void Maniobras(View view){
        maniobra = view.findViewById(R.id.maniobras);
        maniobra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment man = new Maniobras();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, man);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
