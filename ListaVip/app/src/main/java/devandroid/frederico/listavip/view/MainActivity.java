package devandroid.frederico.listavip.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.controller.PessoaController;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    PessoaController controller;


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

        controller = new PessoaController();
        controller.toString();


        pessoa = new Pessoa();

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

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobrenome.setText("");
                editTelefone.setText("");
                editGenero.setText("");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobrenome(editSobrenome.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());
                pessoa.setGenero(editGenero.getText().toString());

                Toast.makeText(MainActivity.this, "Salvo "+pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);

            }
        });



        Log.i("POOandroid",pessoa.toString());

    }
}