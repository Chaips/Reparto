package com.example.repartosahuayo.ui.Gasto;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.repartosahuayo.Adapter.Eventos;
import com.example.repartosahuayo.Adapter.ListAdapterEventos;
import com.example.repartosahuayo.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Combustible extends Fragment {
    private ListView eventosli;
    private static ListAdapterEventos adapter;
    ArrayList<Eventos> items;
    private Button terminar_combustible;
    private ImageView combustibleimage;
    private EditText importe;


    public Combustible() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_combustible, container, false);
        Chronometer simpleChronometer = (Chronometer)rootView.findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.start();
        terminar_combustible = rootView.findViewById(R.id.button3);
        importe = rootView.findViewById(R.id.editText);
        //Mensaje(rootView),
        EventoCombustible(rootView);
        return rootView;
    }

    public void EventoCombustible(View v){
        terminar_combustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApartadoEventosFragment apartadoEventosFragment = new ApartadoEventosFragment();
                Bundle bundle = new Bundle();
                double importes=Double.parseDouble(importe.getText().toString());
                String folio="142128";
                String eventos="Combustible";
                bundle.putDouble("importes",importes);
                bundle.putString("folio",folio);
                bundle.putString("eventos",eventos);
                apartadoEventosFragment.setArguments(bundle);
            }
        });
    }
    /*public Dialog onCreateDialog(View savedInstanceState){
         AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
         builder.setMessage(R.string.dialogo).setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
                //DAR CLIK EN EL BOTON
            }
        });
        builder.setMessage();
        return builder.create();
    }*/

    public void Mensaje(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage("DIALOGO")
                .setTitle("DIALOG")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
