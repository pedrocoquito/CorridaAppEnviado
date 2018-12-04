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
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.corridaapp.service.LoginServiceTask;
import com.example.aluno.corridaapp.service.request.LoginRequest;
import com.example.aluno.corridaapp.service.response.CadastroResponse;
import com.example.aluno.corridaapp.service.response.LoginResponse;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText login, senha;
    private Button botaoLogar;
    public CadastroResponse cadastrado = null;

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
        setContentView(R.layout.activity_login);

        binding();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(login.getText().toString().isEmpty()) && !(senha.getText().toString().isEmpty())) {
                    LoginRequest req = null;
                    try {
                        req = new LoginRequest(login.getText().toString(), senha.getText().toString());
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }
                    LoginResponse resp = null;
                    try {
                        resp = new LoginServiceTask().execute(req).get();
                    } catch (InterruptedException e) {
                        resp = null;
                        Log.e("Erro","onClick: Erro ao consumir o serviço ::"+e.getMessage());
                    } catch (ExecutionException e) {
                        resp = null;
                        Log.e("Erro","onClick: Erro ao consumir o serviço ::"+e.getMessage());
                    }
                    if (resp == null){
                        Toast.makeText(getApplicationContext(), "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
                        login.setText("");
                        senha.setText("");
                        login.requestFocus();
                    }
                    else{
                        Intent itn = new Intent();
                        itn.putExtra("user",resp);
                        setResult(234,itn);
                        finish();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void binding() {
        login = findViewById(R.id.edtLogin);
        senha = findViewById(R.id.edtSenha);
        botaoLogar = findViewById(R.id.btnLogar);
    }
}
