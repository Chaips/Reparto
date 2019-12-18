package com.example.repartosahuayo.ui.Gasto;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.repartosahuayo.Adapter.Eventos;
import com.example.repartosahuayo.Adapter.ListAdapterEventos;
import com.example.repartosahuayo.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


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
    private Spinner  tipodegasto;


    public Combustible() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_combustible, container, false);
        Chronometer simpleChronometer = rootView.findViewById(R.id.simpleChronometer); // initiate a chronometer
        simpleChronometer.start();
        terminar_combustible = rootView.findViewById(R.id.button3);
        importe = rootView.findViewById(R.id.editText);
        tipodegasto = rootView.findViewById(R.id.spinner);
        EventoCombustible(rootView);
        Spinner();
        Back(rootView);
        return rootView;
    }

    private void EventoCombustible(View v){
        terminar_combustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etimportes=importe.getText().toString();
                String folio="142128";
                String eventos="Combustible";
                String selection = tipodegasto.getSelectedItem().toString();
                if(selection.equals("Seleccione")){
                    tipodegasto.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"Opcion no valida debe elegir una opcion diferente de Seleccione",Toast.LENGTH_LONG).show();
                }else if("".equals(etimportes)) {
                    importe.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"No puede estar vacio el importe",Toast.LENGTH_LONG).show();
                }else{

                    double importes = Double.valueOf(etimportes);
                    NumberFormat formatoimporte = NumberFormat.getCurrencyInstance(new Locale("es","MX"));
                    String importesmoneda = formatoimporte.format(importes);
                    getTargetFragment().onActivityResult(getTargetRequestCode(),
                            Activity.RESULT_OK,
                            new Intent().putExtra("eventos",eventos).
                            putExtra("folio",folio).putExtra("importes",importes));
                    getFragmentManager().popBackStack();
                    Toast.makeText(getActivity(),"El importe es de: "+importesmoneda,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Mensaje(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("No puede regresar hasta que termine el evento")
                .setTitle("DIALOG");
        final AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(null != alert && alert.isShowing()) alert.dismiss();
            }
        }, 5000);
    }

    private void Spinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.factura,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipodegasto.setAdapter(adapter);
    }

    private void Back(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if(keyCode == KeyEvent.KEYCODE_BACK) {
                    Mensaje();
                    return true;
                }
                return false;
            }
        } );
    }
}
