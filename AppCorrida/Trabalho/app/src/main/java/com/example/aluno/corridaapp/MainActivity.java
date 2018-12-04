package com.example.aluno.corridaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aluno.corridaapp.fragments.CancelarInscricaoFragment;
import com.example.aluno.corridaapp.fragments.EventosFragment;
import com.example.aluno.corridaapp.fragments.MeusEventosFragment;
import com.example.aluno.corridaapp.fragments.PrincipalFragment;
import com.example.aluno.corridaapp.fragments.RankingFragment;
import com.example.aluno.corridaapp.service.response.CorridaResponse;
import com.example.aluno.corridaapp.service.response.LoginResponse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,PrincipalFragment.OnFragmentInteractionListener,EventosFragment.OnFragmentInteractionListener,RankingFragment.OnFragmentInteractionListener,MeusEventosFragment.OnFragmentInteractionListener,CancelarInscricaoFragment.OnFragmentInteractionListener {

    public static final int REQUEST_LOGIN = 123;
    public static final int RESULT_LOGIN = 234;
    public static final int REQUEST_CADASTRO = 255;
    public static final int RESULT_CADASTRO = 251;
    public static final int REQUEST_INSCREVER = 444;
    public static final int RESULT_INSCRICAO = 221;
    NavigationView navigationView;
    public LoginResponse usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

            showFragment(PrincipalFragment.newInstance("ok", "ok"));



       navigationView = (NavigationView) findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fInject = null;

        if (id == R.id.nav_login) {
            Intent itn = new Intent(getApplicationContext(), LoginActivity.class);

            startActivityForResult(itn, REQUEST_LOGIN);
        } else if (id == R.id.nav_eventos) {
            fInject = MeusEventosFragment.newInstance(usuario.getToken(),"ok");
        } else if (id == R.id.nav_ranking) {
            fInject = RankingFragment.newInstance(usuario.getToken(),"ok");
        }else if (id == R.id.nav_principal) {
            fInject = PrincipalFragment.newInstance(usuario.getToken(),"ok");
        } else if (id == R.id.nav_sair) {
            usuario = null;
            showFragment(PrincipalFragment.newInstance("ok","ok"));
            hideItems();
        } else if (id == R.id.nav_cadastro) {
            Intent itnCad = new Intent(getApplicationContext(),CadastroActivity.class);

            startActivityForResult(itnCad, REQUEST_CADASTRO);
        }

        if (fInject != null){
            showFragment(fInject);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_LOGIN && resultCode == RESULT_LOGIN){
            usuario = (LoginResponse) data.getExtras().getSerializable("user");
            showItems();
            showFragment(PrincipalFragment.newInstance(usuario.getToken(),"ok"));
        }else if(requestCode == REQUEST_CADASTRO && resultCode == RESULT_CADASTRO){
            Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            Intent itn = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(itn, REQUEST_LOGIN);
        }else if(requestCode == REQUEST_INSCREVER && resultCode == RESULT_INSCRICAO){
            CorridaResponse cr = (CorridaResponse) data.getExtras().getSerializable("itemSelecionado");
            showFragment(EventosFragment.newInstance(usuario.getToken(),"ok"));
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void showItems(){
        changeVisibilityMenu(R.id.nav_login,false);
        changeVisibilityMenu(R.id.nav_cadastro,false);
        changeVisibilityMenu(R.id.nav_eventos,true);
        changeVisibilityMenu(R.id.nav_ranking,true);
        changeVisibilityMenu(R.id.nav_sair,true);
        changeVisibilityMenu(R.id.nav_principal,true);
        MenuItem item = navigationView.getMenu().findItem(R.id.item_1).setTitle("Deseja Sair ?");
    }
    private void hideItems(){
        changeVisibilityMenu(R.id.nav_login,true);
        changeVisibilityMenu(R.id.nav_cadastro,true);
        changeVisibilityMenu(R.id.nav_eventos,false);
        changeVisibilityMenu(R.id.nav_principal,false);
        changeVisibilityMenu(R.id.nav_ranking,false);
        changeVisibilityMenu(R.id.nav_sair,true);
    }
    private void changeVisibilityMenu(int idMenu, boolean visibility){
        MenuItem item;
        item = navigationView.getMenu().findItem(idMenu);
        item.setVisible(visibility);
    }
    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.conteudoframe,fragment).commitAllowingStateLoss();
    }
}
