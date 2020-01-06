package com.example.repartosahuayo.ui.Factura;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.repartosahuayo.R;
import com.example.repartosahuayo.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView lle,inicio,fin,salida;
    private TextView Llegada,Inicio,Fin,Salida;
    Date date = new Date();
    SimpleDateFormat dateFormat;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        lle=rootView.findViewById(R.id.llegada);
        Llegada=rootView.findViewById(R.id.textView14);
        Inicio=rootView.findViewById(R.id.textView15);
        inicio = rootView.findViewById(R.id.inicio);
        fin=rootView.findViewById(R.id.fin);
        Fin=rootView.findViewById(R.id.textView16);
        salida=rootView.findViewById(R.id.salida);
        Salida=rootView.findViewById(R.id.textView17);
        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tabs);
        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(new Llegada2Fragment(), "Llegada");
        adapter.addFragment(new ContactosFragment(), "Contactos");
        adapter.addFragment(new InformacionFragment(), "Detalles");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Llegada2Fragment vista1Fragment = new Llegada2Fragment();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.viewpager, vista1Fragment);
        fragmentTransaction.commit();
        Lleg(rootView);
        Inicio(rootView);
        Fin(rootView);
        Salida(rootView);
        return rootView;
    }
    private void Lleg(final View view){
        lle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llegada.setTextColor(Color.parseColor("#ed1c24"));
                lle.setImageResource(R.mipmap.deliveryred1);
                Inicio.setTextColor(Color.parseColor("#17134b"));
                inicio.setImageResource(R.mipmap.delivery2);
                Fin.setTextColor(Color.parseColor("#17134b"));
                fin.setImageResource(R.mipmap.delivery3);
                Salida.setTextColor(Color.parseColor("#17134b"));
                salida.setImageResource(R.mipmap.delivery4);
                dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String hora_llegada = dateFormat.format(date);
                Toast.makeText(getActivity(),"Su hora de llegada fue: " +hora_llegada,Toast.LENGTH_LONG).show();
            }
        });

    }
    private void Inicio(View v){
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llegada.setTextColor(Color.parseColor("#17134b"));
                lle.setImageResource(R.mipmap.delivery1);
                Inicio.setTextColor(Color.parseColor("#ed1c24"));
                inicio.setImageResource(R.mipmap.deliveryred2);
                Fin.setTextColor(Color.parseColor("#17134b"));
                fin.setImageResource(R.mipmap.delivery3);
                Salida.setTextColor(Color.parseColor("#17134b"));
                salida.setImageResource(R.mipmap.delivery4);
                dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String hora_inicio = dateFormat.format(date);
                Toast.makeText(getActivity(),"Su hora de Inicio es: " + hora_inicio,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void Fin(View v){
        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llegada.setTextColor(Color.parseColor("#17134b"));
                lle.setImageResource(R.mipmap.delivery1);
                Inicio.setTextColor(Color.parseColor("#17134b"));
                inicio.setImageResource(R.mipmap.delivery2);
                Fin.setTextColor(Color.parseColor("#ed1c24"));
                fin.setImageResource(R.mipmap.deliveryred3);
                Salida.setTextColor(Color.parseColor("#17134b"));
                salida.setImageResource(R.mipmap.delivery4);
                dateFormat = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
                String hora_final = dateFormat.format(date);
                Toast.makeText(getActivity(),"Su hora de fin es: " + hora_final, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void Salida(View v){
        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Llegada.setTextColor(Color.parseColor("#17134b"));
                lle.setImageResource(R.mipmap.delivery1);
                Inicio.setTextColor(Color.parseColor("#17134b"));
                inicio.setImageResource(R.mipmap.delivery2);
                Fin.setTextColor(Color.parseColor("#17134b"));
                fin.setImageResource(R.mipmap.delivery3);
                Salida.setTextColor(Color.parseColor("#ed1c24"));
                salida.setImageResource(R.mipmap.deliveryredp4);
                dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String hora_salida = dateFormat.format(date);
                Toast.makeText(getActivity(),"Su hora de salida es: " + hora_salida, Toast.LENGTH_LONG).show();
            }
        });
    }
}
