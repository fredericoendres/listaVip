package devandroid.frederico.listavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;

    String dadosPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome("Frederico");
        pessoa.setSobrenome("Endres");
        pessoa.setGenero("Masculino");
        pessoa.setTelefone("(48)99983-4848");

/*        dadosPessoa = "Primeiro Nome: ";
        dadosPessoa += pessoa.getPrimeiroNome();
        dadosPessoa += "\nSobrenome: ";
        dadosPessoa += pessoa.getSobrenome();
        dadosPessoa += "\nGenero: ";
        dadosPessoa += pessoa.getGenero();
        dadosPessoa += "\nTelefone: ";
        dadosPessoa += pessoa.getTelefone(); */

        Log.i("POOandroid",pessoa.toString());

    }
}