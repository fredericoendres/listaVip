package devandroid.frederico.listavip.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.frederico.listavip.model.Pessoa;

public class PessoaController {

    @NonNull
    @Override
    public String toString() {

        Log.d("MVC_Controller", "Controller iniciado");

        return super.toString();
    }

    public void salvar(Pessoa pessoa) {

        Log.d("MVC_Controller", "Salvo: "+pessoa.toString());

    }
}
