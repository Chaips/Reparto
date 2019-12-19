package com.example.repartosahuayo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.repartosahuayo.R;

import java.util.ArrayList;

public class ListAdapterEventos extends ArrayAdapter<Eventos> implements View.OnClickListener {
    private ArrayList<Eventos> eventos;
    Context mContext;
    private LayoutInflater vi=null;
    private int viewresourceid=0;
    private static class ViewHolder{
        public TextView evento,folio,importe;
    }
    public ListAdapterEventos(ArrayList<Eventos>items, Context context, int viewreourceid){
        super(context, R.layout.eventos,items);
        vi=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewreourceid=viewreourceid;
        this.eventos = items;
        this.mContext = context;
    }
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Eventos eventos=(Eventos) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Eventos dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ListAdapterEventos.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
             convertView=vi.inflate(viewresourceid,null);
             TextView tv=convertView.findViewById(R.id.eventosc);
             tv.setText("Combustible");

            /*viewHolder = new ListAdapterEventos.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.eventos, parent, false);
            viewHolder.evento = convertView.findViewById(R.id.eventosc);
            viewHolder.folio = convertView.findViewById(R.id.folio);
            viewHolder.importe = convertView.findViewById(R.id.importe);
            result=convertView;
            convertView.setTag(viewHolder);*/
        } else {
            viewHolder = (ListAdapterEventos.ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*lastPosition = position;
        viewHolder.evento.setText(dataModel.getEvento());
        viewHolder.folio.setText(dataModel.getFolio());
        viewHolder.importe.setText(String.valueOf(dataModel.getImporte()));*/
        return convertView;
    }
}
