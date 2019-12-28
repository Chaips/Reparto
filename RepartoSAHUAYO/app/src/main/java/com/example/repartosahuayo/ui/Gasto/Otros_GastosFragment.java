package com.example.repartosahuayo.ui.Gasto;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.repartosahuayo.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class Otros_GastosFragment extends Fragment {
    private EditText otro_importe;
    private Button terminar_otros_gastos;
    private Spinner tipo_de_gasto_otros_gastos;


    public Otros_GastosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_otros__gastos, container, false);
        Chronometer simpleChronometer = rootView.findViewById(R.id.simpleChronometer4);
        simpleChronometer.start();
        tipo_de_gasto_otros_gastos = rootView.findViewById(R.id.importe_otros);
        otro_importe = rootView.findViewById(R.id.editText2);
        otro_importe.addTextChangedListener(onTextChangedListener());
        EventoOtrosGastos(rootView);
        Spinner();
        Back(rootView);
        return rootView;
    }

    private void EventoOtrosGastos(View view){
        terminar_otros_gastos = view.findViewById(R.id.terminar_otros);
        terminar_otros_gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etimportes=otro_importe.getText().toString().replaceAll(",", "");
                String folio="142128";
                String eventos="Otros Gastos";
                String selection = tipo_de_gasto_otros_gastos.getSelectedItem().toString();
                if(selection.equals("Seleccione")){
                    tipo_de_gasto_otros_gastos.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"Opcion no valida debe elegir una opcion diferente de Seleccione",Toast.LENGTH_LONG).show();
                }else if("".equals(etimportes)) {
                    otro_importe.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"No puede estar vacio el importe",Toast.LENGTH_LONG).show();
                }else {
                    double importes = Double.valueOf(etimportes);
                    NumberFormat formatoimporte = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
                    String importesmoneda = formatoimporte.format(importes);
                    getTargetFragment().onActivityResult(getTargetRequestCode(),
                            Activity.RESULT_OK,
                            new Intent().putExtra("eventos", eventos).
                                    putExtra("folio", folio).putExtra("importes", importes));
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    private void Spinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.factura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo_de_gasto_otros_gastos.setAdapter(adapter);
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
                if (null != alert && alert.isShowing()) alert.dismiss();
            }
        }, 3000);
    }

    private void Back(View view){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    Mensaje();
                    return true;
                }
                return false;
            }
        });
    }

    private TextWatcher onTextChangedListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                otro_importe.removeTextChangedListener(this);
                try {
                    String orginalString = s.toString();
                    Long longval;
                    if (orginalString.contains(",")){
                        orginalString = orginalString.replaceAll("," ,"");
                    }
                    longval = Long.parseLong(orginalString);
                    DecimalFormat format = (DecimalFormat) NumberFormat
                            .getCurrencyInstance(new Locale("es","MX"));
                    format.applyPattern("#,###,###,###");
                    String formattedString = format.format(longval);
                    otro_importe.setText(formattedString);
                    otro_importe.setSelection(otro_importe.getText().length());
                }catch (NumberFormatException nfe){
                    nfe.printStackTrace();
                    Timber.d("Excepcion del formarto de numero"+nfe);
                }
                otro_importe.addTextChangedListener(this);

            }
        };
    }
}
