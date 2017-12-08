package com.example.rodrigo.tp3_desenvolvimento_rodrigo_filomeno;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.firebase.database.DatabaseReference;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements ValidationListener {

    Validator validator;

    @NotEmpty(message = "Campo não pode ficar em branco")
    EditText Nome;

    @NotEmpty(message = "Campo não pode ficar em branco")
    @Password(min = 6, message = "minimo de 6 caracteres")
    EditText Senha;

    @NotEmpty(message = "Campo não pode ficar em branco")
    @Email(message = "Digite um e-mail valido")
    EditText Email;

    @NotEmpty(message = "Campo não pode ficar em branco")
    EditText Telefone;

    @NotEmpty(message = "Campo não pode ficar em branco")
    EditText Celular;

    @NotEmpty(message = "Campo não pode ficar em branco")
    @Length(min = 14, max = 14, message = "coloque um CPF valido")
    EditText Cpf;

    @NotEmpty(message = "Campo não pode ficar em branco")
    EditText Cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validator = new Validator(this);
        validator.setValidationListener(this);

        Nome = (EditText) findViewById(R.id.etNome);
        Senha = (EditText) findViewById(R.id.etSenha);
        Email = (EditText) findViewById(R.id.etEmail);
        Telefone = (EditText) findViewById(R.id.etTelefone);
        Celular = (EditText) findViewById(R.id.etCelular);
        Cpf = (EditText) findViewById(R.id.etCpf);
        Cidade = (EditText) findViewById(R.id.etCidade);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(Cpf, smf);
        Cpf.addTextChangedListener(mtw);

        SimpleMaskFormatter smf2 = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw2 = new MaskTextWatcher(Telefone, smf2);
        Telefone.addTextChangedListener(mtw2);

        SimpleMaskFormatter smf3 = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtw3 = new MaskTextWatcher(Celular, smf3);
        Celular.addTextChangedListener(mtw3);


    }

    public void Salvar(View v){
        validator.validate();

    }

    @Override
    public void onValidationSucceeded() {
        Usuario usuario = new Usuario(UUID.randomUUID().toString(),Nome.getText().toString(),Senha.getText().toString(),Email.getText().toString(),
                Telefone.getText().toString(),Celular.getText().toString(),Cpf.getText().toString(),Cidade.getText().toString());

        usuario.Salvar();
        Mensagem("usuário criado");
        Clean();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Mensagem(message);
            }
        }
    }
    private void Mensagem (String msg){

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }
    public void Limpar(View v){
        Clean();
    }
    public void Clean(){
        Nome.setText("");
        Telefone.setText("");
        Senha.setText("");
        Email.setText("");
        Telefone.setText("");
        Celular.setText("");
        Cpf.setText("");
        Cidade.setText("");
    }

    public void btnListaUsuarios(View v){
        Intent intent = new Intent (MainActivity.this, UsuariosActivity.class);
        startActivity(intent);
    }
}