package devandroid.frederico.listavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome("Frederico");
        pessoa.setSobrenome("Endres");
        pessoa.setGenero("Masculino");
        pessoa.setTelefone("(48)99983-4848");

    }
}