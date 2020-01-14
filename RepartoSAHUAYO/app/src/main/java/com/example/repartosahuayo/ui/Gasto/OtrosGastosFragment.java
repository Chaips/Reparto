package com.example.repartosahuayo.ui.Gasto;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.repartosahuayo.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtrosGastosFragment extends Fragment {
    private Button terminar_otro_gasto;
    private EditText importe_gasto;
    private Spinner tipodegasto;


    public OtrosGastosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_otros_gastos, container, false);
        terminar_otro_gasto = rootView.findViewById(R.id.terminar_otrod_gastos);
        importe_gasto = rootView.findViewById(R.id.importe_otros_gastos);
        tipodegasto = rootView.findViewById(R.id.spinner_otros_gastos);
        GastoManiobras(rootView);
        Spinner();
        Back(rootView);
        importe_gasto.addTextChangedListener(onTextChangedListener());
        return rootView;
    }


    private void GastoManiobras(View v){
        terminar_otro_gasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etimportes=importe_gasto.getText().toString().replaceAll(",", "");
                String folio="142128";
                String eventos="Combustible";
                String selection = tipodegasto.getSelectedItem().toString();
                if(selection.equals("Seleccione")){
                    tipodegasto.setBackgroundResource(R.drawable.borde);
                    Toast.makeText(getActivity(),"Opcion no valida debe elegir una opcion diferente de Seleccione",Toast.LENGTH_LONG).show();
                }else if("".equals(etimportes)) {
                    importe_gasto.setBackgroundResource(R.drawable.borde);
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
                    //Toast.makeText(getActivity(),"El importe es de: "+importesmoneda,Toast.LENGTH_LONG).show();
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
        }, 3000);
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


    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int inicio, int valor, int antes) {

            }

            @Override
            public void onTextChanged(CharSequence s, int inicio, int antes, int valor) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                importe_gasto.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();
                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);
                    DecimalFormat formatter = (DecimalFormat) NumberFormat
                            .getCurrencyInstance(new Locale("es","MX"));
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);
                    importe_gasto.setText(formattedString);
                    importe_gasto.setSelection(importe_gasto.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    Timber.d("Excepcion del formarto de numero"+nfe);
                }
                importe_gasto.addTextChangedListener(this);
            }
        };
    }
}
