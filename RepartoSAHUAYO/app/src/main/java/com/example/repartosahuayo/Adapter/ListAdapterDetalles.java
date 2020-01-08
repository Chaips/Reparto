package com.example.repartosahuayo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.repartosahuayo.R;

import java.util.ArrayList;

public class ListAdapterDetalles extends ArrayAdapter<Datos> implements View.OnClickListener {
    private ArrayList<Datos> datos;
    Context mcontext;
    private static class ViewHolder{
        public TextView clave_producto,nombre_del_producto,total;
    }
    public ListAdapterDetalles(ArrayList<Datos>items, Context context){
        super(context, R.layout.detalles,items);
        this.datos=items;
        this.mcontext=context;
    }
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Datos datos=(Datos) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Datos dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.detalles, parent, false);
            viewHolder.clave_producto = convertView.findViewById(R.id.clave);
            viewHolder.nombre_del_producto = convertView.findViewById(R.id.producto);
            viewHolder.total = convertView.findViewById(R.id.total);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;
        viewHolder.nombre_del_producto.setText(dataModel.getNombre_producto());
        viewHolder.clave_producto.setText(dataModel.getClave_producto());
        viewHolder.total.setText(String.valueOf(dataModel.getTotal()));
        return convertView;
    }
}