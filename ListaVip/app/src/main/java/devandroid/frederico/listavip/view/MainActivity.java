package devandroid.frederico.listavip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.controller.PessoaAdapter;
import devandroid.frederico.listavip.database.ListaVipDB;
import devandroid.frederico.listavip.model.Pessoa;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PessoaAdapter.OnEditClickListener {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private ListaVipDB listaVipDB;
    private PessoaAdapter adapter;

    @Override
    public void onEditClick(int pessoaId) {
        Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
        intent.putExtra("pessoaId", pessoaId);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.escolha_dia){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EscolhaDiaFragment()).commit();
        } else {
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaVipDB = new ListaVipDB(this);
        List<Pessoa> pessoaList = listaVipDB.listarDadosHoje();

        adapter = new PessoaAdapter(pessoaList);
        recyclerView.setAdapter(adapter);

        adapter.setOnEditClickListener(this);
        adapter.setOnDeleteClickListener(new PessoaAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final Pessoa pessoa) {
                Snackbar snackbar = Snackbar.make(recyclerView, "Pessoa a ser deletada", Snackbar.LENGTH_LONG)
                        .setAction("CONFIRMAR", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                listaVipDB.deletarObjeto(pessoa);
                                pessoaList.remove(pessoa);
                                adapter.notifyDataSetChanged();
                            }
                        });
                snackbar.show();
            }
        });

        recyclerView.setAdapter(adapter);

    }
}