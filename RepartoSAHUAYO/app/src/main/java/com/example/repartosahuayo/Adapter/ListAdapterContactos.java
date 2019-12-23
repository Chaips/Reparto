package com.example.repartosahuayo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.repartosahuayo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListAdapterContactos extends ArrayAdapter<Contactos> implements View.OnClickListener {
    private ArrayList<Contactos> contactos;
    Context mContext;
    private static class ViewHolder{
        public TextView contacto,tipo_contacto;
    }
    public ListAdapterContactos(ArrayList<Contactos>items, Context context){
        super(context, R.layout.contactos,items);
        this.contactos = items;
        this.mContext = context;
    }
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Contactos contactos=(Contactos) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contactos dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ListAdapterContactos.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.contactos, parent, false);
            viewHolder.contacto = convertView.findViewById(R.id.contacto);
            viewHolder.tipo_contacto = convertView.findViewById(R.id.tipo_contacto);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListAdapterContactos.ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;
        viewHolder.contacto.setText(dataModel.getContacto());
        viewHolder.tipo_contacto.setText(dataModel.getTipo_contacto());
        return convertView;
    }
}