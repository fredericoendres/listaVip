package devandroid.frederico.listavip.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.controller.GeneroController;
import devandroid.frederico.listavip.controller.PessoaController;
import devandroid.frederico.listavip.model.Pessoa;

public class SpinnerActivity extends AppCompatActivity {

    PessoaController controller;
    GeneroController generoController;
    Pessoa pessoa;
    List<String> tiposDeGenero;

    EditText editPrimeiroNome;
    EditText editSobrenome;
    EditText editCpf;
    EditText editTelefone;

    private String selectedGenero;
    Button btnLimpar;
    Button btnSalvar;

    ImageButton btnVoltar;
    List<Pessoa> dados;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        controller = new PessoaController(SpinnerActivity.this);
        dados = controller.getListaDeDados();

        generoController = new GeneroController();
        tiposDeGenero = generoController.dadosParaSpinner();

        pessoa = new Pessoa();
        controller.buscar(pessoa);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editCpf = findViewById(R.id.editCpf);
        editTelefone = findViewById(R.id.editTelefone);
        spinner = findViewById(R.id.spinner);

        btnVoltar = findViewById(R.id.btnVerMais);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);

        int pessoaId = getIntent().getIntExtra("pessoaId", -1);
        if (pessoaId != -1) {
            Pessoa pessoa = controller.getPessoaById(pessoaId);

            editPrimeiroNome.setText(pessoa.getPrimeiroNome());
            editSobrenome.setText(pessoa.getSobrenome());
            editTelefone.setText(pessoa.getTelefone());
            editCpf.setText(pessoa.getCpf());

            int generoIndex = tiposDeGenero.indexOf(pessoa.getGenero());
            if (generoIndex != -1) {
                spinner.setSelection(generoIndex);
            }
        }

        // Criando adapter a colocadn contexto
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                generoController.dadosParaSpinner());
        // Inserindo layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        // Injetando
        spinner.setAdapter(adapter);

        int generoIndex = tiposDeGenero.indexOf(pessoa.getGenero());

        if (generoIndex != -1) {
            spinner.setSelection(generoIndex);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pessoa.setGenero((String) parent.getItemAtPosition(position));
                selectedGenero = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobrenome.setText("");
                editTelefone.setText("");
                editCpf.setText("");

                controller.limpar();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobrenome(editSobrenome.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());
                pessoa.setCpf(editCpf.getText().toString());
                pessoa.setGenero(selectedGenero);

                Toast.makeText(SpinnerActivity.this, "Salvo "+pessoa.toString(), Toast.LENGTH_LONG).show();

                controller.salvar(pessoa);

                editPrimeiroNome.setText("");
                editSobrenome.setText("");
                editTelefone.setText("");
                editCpf.setText("");

            }
        });



        Log.i("POOandroid",pessoa.toString());


    }

}
