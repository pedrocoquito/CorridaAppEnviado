package com.example.aluno.corridaapp.service;

import android.os.AsyncTask;

import com.example.aluno.corridaapp.service.request.CadastroRequest;
import com.example.aluno.corridaapp.service.response.CadastroResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CadastroServiceTask extends AsyncTask<CadastroRequest,Void,CadastroResponse> {
    @Override
    protected CadastroResponse doInBackground(CadastroRequest... cadastroRequests) {
        URL url = null;

        try {
            url = new URL("https://api.davesmartins.com.br/api/corredor");

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(95*1000);
            urlConnection.setConnectTimeout(95*1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Environment", "android");

            String cadastro = new Gson().toJson(cadastroRequests[0]);
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(cadastro.getBytes());

            urlConnection.connect();

            String finalJson = "";

            if (urlConnection.getResponseCode() == 200){
                InputStream responseBody = urlConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody,"UTF-8");
                BufferedReader reader = new BufferedReader(responseBodyReader);
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                finalJson = buffer.toString();
            }else{
                finalJson = urlConnection.getResponseCode()+"";
            }

            CadastroResponse cadres = new Gson().fromJson(finalJson.toString(),CadastroResponse.class);

            return cadres;

        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
