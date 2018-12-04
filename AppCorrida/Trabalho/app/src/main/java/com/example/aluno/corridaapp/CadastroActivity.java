package com.example.aluno.corridaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.corridaapp.service.CadastroServiceTask;
import com.example.aluno.corridaapp.service.request.CadastroRequest;
import com.example.aluno.corridaapp.service.response.CadastroResponse;

import java.util.concurrent.ExecutionException;

public class CadastroActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText cadLogin, cadSenha, cadEmail, cadNome;
    private Button botaoCadastrar;
    private ImageView image;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        binding();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastroRequest req = new CadastroRequest(0,cadNome.getText().toString(),cadEmail.getText().toString(),cadLogin.getText().toString(),cadSenha.getText().toString());

                CadastroResponse resp = null;
                try {
                    resp = new CadastroServiceTask().execute(req).get();
                } catch (InterruptedException e) {
                    resp = null;
                    Log.e("Erro","onClick: Erro ao consumir o serviço ::"+e.getMessage());
                } catch (ExecutionException e) {
                    resp = null;
                    Log.e("Erro","onClick: Erro ao consumir o serviço ::"+e.getMessage());
                }

                if (resp == null){
                    Toast.makeText(getApplicationContext(), "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
                    cadNome.setText("");
                    cadEmail.setText("");
                    cadLogin.setText("");
                    cadSenha.setText("");
                    cadNome.requestFocus();
                }
                else{
                    Intent itn = new Intent();
                    itn.putExtra("cad",resp);
                    setResult(251,itn);
                    finish();
                }
            }
        });

    }

    private void binding() {
        cadEmail = findViewById(R.id.edtEmail);
        cadSenha = findViewById(R.id.edtCadSenha);
        cadLogin = findViewById(R.id.edtCadLogin);
        cadNome = findViewById(R.id.edtCadNome);
        botaoCadastrar = findViewById(R.id.btnCadastrar);
        image = findViewById(R.id.imgAtletaCad);
    }

}
