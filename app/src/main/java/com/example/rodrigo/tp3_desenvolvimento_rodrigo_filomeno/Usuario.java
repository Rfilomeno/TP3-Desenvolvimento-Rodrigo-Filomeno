package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cfilo on 07/12/2017.
 */

public class Usuario {

    String id;
    String Nome;
    String Senha;
    String Email;
    String Telefone;
    String Celular;
    String Cpf;
    String Cidade;

    public Usuario(){

    }
    public Usuario(String id,String nome,String senha,String email,String telefone,String celular,String cpf,String cidade){
        this.id = id;
        this.Nome = nome;
        this.Senha = senha;
        this.Email = email;
        this.Telefone = telefone;
        this.Celular = celular;
        this.Cpf = cpf;
        this.Cidade = cidade;
    }

public void Salvar(){
    try{
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child(String.valueOf(getId())).setValue(this);
    }catch (Exception e){
        Log.e("erro", e.toString());
    }
    ;
}


    public String getId() {
        return id;
    }

}
