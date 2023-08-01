package devandroid.frederico.listavip.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import devandroid.frederico.listavip.database.ListaVipDB;
import devandroid.frederico.listavip.model.Pessoa;
import devandroid.frederico.listavip.view.SpinnerActivity;

public class PessoaController extends ListaVipDB {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listavip";

    public PessoaController(SpinnerActivity mainActivity){
        super(mainActivity);
        preferences =
                mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {

        Log.d("MVC_Controller", "Controller iniciado");

        return super.toString();
    }

    public void salvar(Pessoa pessoa) {
        ContentValues dados = new ContentValues();

        Log.d("MVC_Controller", "Salvo: "+pessoa.toString());

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobrenome", pessoa.getSobrenome());
        listaVip.putString("genero", pessoa.getGenero());
        listaVip.putString("telefone", pessoa.getTelefone());
        listaVip.putString("cpf", pessoa.getCpf());
        listaVip.apply();

        dados.put("primeiroNome", pessoa.getPrimeiroNome());
        dados.put("sobrenome", pessoa.getSobrenome());
        dados.put("telefone", pessoa.getTelefone());
        dados.put("cpf", pessoa.getCpf());
        dados.put("generoInformado", pessoa.getGenero());

        salvarObjeto(pessoa);

    }

    private void salvarObjeto(Pessoa pessoa) {
        salvarObjeto("Lista", pessoa);
    }

    public List<Pessoa> getListaDeDados(){
        return listarDadosHoje();
    }

    public Pessoa buscar(Pessoa pessoa){

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome",""));
        pessoa.setSobrenome(preferences.getString("sobrenome",""));
        pessoa.setTelefone(preferences.getString("telefone",""));
        pessoa.setGenero(preferences.getString("genero",""));

        return pessoa;
    }


    private void deletarObjetico(String lista, int id) {
    }

    public void limpar(){

        listaVip.clear();
        listaVip.apply();

    }

}
