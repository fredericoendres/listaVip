package devandroid.frederico.listavip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import devandroid.frederico.listavip.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.hoje_dia){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HojeFragment()).commit();
        } else if (item.getItemId() == R.id.escolha_dia){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EscolhaDiaFragment()).commit();
        } else {
            Intent intent = new Intent(this, SpinnerActivity.class);
            startActivity(intent);
        }

        /* switch (item.getItemId()) {
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

        }*/

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




       /* Pessoa objAlteracao = dados.get(1);
        objAlteracao.setPrimeiroNome("Novo nome");
        objAlteracao.setSobrenome("Novo sobrenome");
        objAlteracao.setGenero("Novo genero");
        objAlteracao.setTelefone("Novo telefone");

        //controller.alterar(objAlteracao);

        controller.deletar(1);*/





    }
}