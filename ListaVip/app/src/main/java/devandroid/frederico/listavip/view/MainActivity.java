package devandroid.frederico.listavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoa;

    EditText editPrimeiroNome;
    EditText editSobrenome;
    EditText editGenero;
    EditText editTelefone;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;
    ImageButton btnVerMais;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome("Frederico");
        pessoa.setSobrenome("Endres");
        pessoa.setGenero("Masculino");
        pessoa.setTelefone("(48)99983-4848");

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editGenero = findViewById(R.id.editGenero);
        editTelefone = findViewById(R.id.editTelefone);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVerMais = findViewById(R.id.btnVerMais);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobrenome.setText(pessoa.getSobrenome());
        editGenero.setText(pessoa.getGenero());
        editTelefone.setText(pessoa.getTelefone());




        Log.i("POOandroid",pessoa.toString());

    }
}