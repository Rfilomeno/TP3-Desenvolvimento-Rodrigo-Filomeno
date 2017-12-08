package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 08/12/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<Usuario> {

    private ArrayList<Usuario> usuario;
    private Context context;


    public CustomListViewAdapter(Context c,ArrayList<Usuario> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuario = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = null;

        if (usuario != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listausuario, parent , false);
            TextView txtViewNome = (TextView) view.findViewById(R.id.txtViewNome);

            Usuario usuarios = usuario.get(position);
            txtViewNome.setText(usuarios.Nome.toString());

        }
        return view;


    }
}
