package devandroid.frederico.listavip.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import devandroid.frederico.listavip.EscolhaDiaFragment;
import devandroid.frederico.listavip.HojeFragment;
import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.controller.GeneroController;
import devandroid.frederico.listavip.controller.PessoaController;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    PessoaController controller;
    GeneroController generoController;
    Pessoa pessoa;
    List<String> tiposDeGenero;

    EditText editPrimeiroNome;
    EditText editSobrenome;
    EditText editGenero;
    EditText editTelefone;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;
    ImageButton btnVerMais;

    List<Pessoa> dados;
    Spinner spinner;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.hoje_dia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HojeFragment()).commit();
                break;

            case R.id.escolha_dia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EscolhaDiaFragment()).commit();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        controller = new PessoaController(MainActivity.this);
        dados = controller.getListaDeDados();

        Pessoa objAlteracao = dados.get(1);
        objAlteracao.setPrimeiroNome("Novo nome");
        objAlteracao.setSobrenome("Novo sobrenome");
        objAlteracao.setGenero("Novo genero");
        objAlteracao.setTelefone("Novo telefone");

        //controller.alterar(objAlteracao);

        controller.deletar(1);

        generoController = new GeneroController();
        tiposDeGenero = generoController.dadosParaSpinner();

        pessoa = new Pessoa();
        controller.buscar(pessoa);

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editSobrenome = findViewById(R.id.editSobrenome);
        editGenero = findViewById(R.id.editGenero);
        editTelefone = findViewById(R.id.editTelefone);
        spinner = findViewById(R.id.spinner);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobrenome.setText(pessoa.getSobrenome());
        editTelefone.setText(pessoa.getTelefone());
        editGenero.setText(pessoa.getGenero());


        btnFinalizar = findViewById(R.id.btnFinalizar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnVerMais = findViewById(R.id.btnVerMais);

        // Criando adapter a colocadn contexto
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                generoController.dadosParaSpinner());
        // Inserindo layout
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        // Injetando
        spinner.setAdapter(adapter);


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

               controller.limpar();
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