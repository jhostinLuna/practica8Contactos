package com.juangg.practica8contactos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactosAdapter extends ArrayAdapter<cContacto> {

    private Context miContext;
    private int miResource;


    public ContactosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<cContacto> objects) {
        super(context, resource, objects);
        this.miContext = context;
        this.miResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(miContext);

        convertView = layoutInflater.inflate(miResource, parent,false);

        //declaro las 3 view de mi item customizado

        TextView txtcodigo = convertView.findViewById(R.id.iid);
        TextView txtnombre = convertView.findViewById(R.id.inombre);
        TextView txtapellido = convertView.findViewById(R.id.iapellido);
        TextView txttelefono = convertView.findViewById(R.id.itelefono);

        txtcodigo.setText(getItem(position).getCodigo());
        txtnombre.setText(getItem(position).getNombre());
        txtapellido.setText(getItem(position).getApellido());
        txttelefono.setText(getItem(position).getTelefono());

        return convertView;


    }


}





