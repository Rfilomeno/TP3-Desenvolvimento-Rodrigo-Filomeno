package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
    referenciaFirebase.child("usuario").child(String.valueOf(getId())).setValue(this);
}

@Exclude
public Map<String, Object> toMap(){
    HashMap<String,Object> hashMapUsuario = new HashMap<>();

    hashMapUsuario.put("email", getEmail());
    hashMapUsuario.put("nome", getNome());
    hashMapUsuario.put("senha", getSenha());
    hashMapUsuario.put("telefone", getTelefone());
    hashMapUsuario.put("celular", getCelular());
    hashMapUsuario.put("cpf", getCpf());
    hashMapUsuario.put("cidade", getCidade());

    return hashMapUsuario;
}


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }


}
