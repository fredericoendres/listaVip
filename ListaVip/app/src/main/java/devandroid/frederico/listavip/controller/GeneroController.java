package devandroid.frederico.listavip.controller;

import java.util.ArrayList;
import java.util.List;

import devandroid.frederico.listavip.model.Genero;

public class GeneroController {

    private List listGeneros;

    public List getGenero() {

        listGeneros = new ArrayList<Genero>();

        listGeneros.add(new Genero("Masculino"));
        listGeneros.add(new Genero("Feminino"));
        listGeneros.add(new Genero("Outro"));

        return listGeneros;

    }

    public ArrayList<String> dadosParaSpinner(){

        ArrayList<String> dados = new ArrayList<>();

        for (int i = 0; i < getGenero().size(); i++) {

            Genero objeto = (Genero) getGenero().get(i);
            dados.add(objeto.getGeneroInformado());

        }
        return dados;
    }


}
