package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Usuario> adapter;
    private ArrayList<Usuario> usuarios;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        usuarios = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomListViewAdapter(this, usuarios);

        listView.setAdapter(adapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("usuarios");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuarios = (Usuario) parent.getItemAtPosition(position);

                AlertDialog.Builder adb = new AlertDialog.Builder(UsuariosActivity.this);
                adb.setTitle(usuarios.Nome.toString());
                adb.setMessage("E-mail: " + usuarios.Email.toString()
                        + " \nSenha: " + usuarios.Senha.toString()
                        + "\nTelefone: " + usuarios.Telefone.toString()
                        + "\nCelular: " + usuarios.Celular.toString()
                        + "\nCpf: " + usuarios.Cpf.toString()
                        + "\nCidade: " + usuarios.Cidade.toString());
                adb.show();


            }
        });



        valueEventListenerUsuarios = firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usuarios.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    Usuario usuariosNovo = dados.getValue(Usuario.class);

                    usuarios.add(usuariosNovo);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerUsuarios);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerUsuarios);
    }

}
