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
public class PernoctarFragment extends Fragment {
    private Spinner tipo_de_gasto_pernoctar;
    private EditText importe_pernoctar;
    private Button terminar_pernoctar;


    public PernoctarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pernoctar, container, false);
        Chronometer simpleChronometer = rootView.findViewById(R.id.simpleChronometer3);
        tipo_de_gasto_pernoctar = rootView.findViewById(R.id.spinner3);
        importe_pernoctar = rootView.findViewById(R.id.editText_pernoctar);
        importe_pernoctar.addTextChangedListener(onTextChangedListener());
        simpleChronometer.start();
        EventoPernoctar(rootView);
        Spinner();
        Back(rootView);

        return rootView;
    }

    private void EventoPernoctar(View view){
        terminar_pernoctar = view.findViewById(R.id.terminar_pernoctar);
        terminar_pernoctar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etimportes=importe_pernoctar.getText().toString().replaceAll(",", "");
                String folio="142128";
                String eventos="Comida";
                String selection = tipo_de_gasto_pernoctar.getSelectedItem().toString();
                if(selection.equals("Seleccione")){
                    tipo_de_gasto_pernoctar.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"Opcion no valida debe elegir una opcion diferente de Seleccione",Toast.LENGTH_LONG).show();
                }else if("".equals(etimportes)) {
                    importe_pernoctar.setBackgroundResource(R.drawable.borde);
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
        tipo_de_gasto_pernoctar.setAdapter(adapter);
    }

    private void Mensaje() {
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
                importe_pernoctar.removeTextChangedListener(this);
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
                    importe_pernoctar.setText(formattedString);
                    importe_pernoctar.setSelection(importe_pernoctar.getText().length());
                }catch (NumberFormatException nfe){
                    nfe.printStackTrace();
                    Timber.d("Excepcion del formarto de numero"+nfe);
                }
                importe_pernoctar.addTextChangedListener(this);

            }
        };
    }
}
