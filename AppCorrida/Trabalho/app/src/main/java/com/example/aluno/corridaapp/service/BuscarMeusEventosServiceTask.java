package com.example.aluno.corridaapp.service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.aluno.corridaapp.service.response.CorridaResponse;
import com.example.aluno.corridaapp.service.response.MeusEventosResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class BuscarMeusEventosServiceTask extends AsyncTask<String,Void,MeusEventosResponse> {
    @Override
    protected MeusEventosResponse doInBackground(String... code) {
        URL url = null;

        try {
            url = new URL("https://api.davesmartins.com.br/api/corredor");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(95*1000);
            urlConnection.setConnectTimeout(95*1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Environment", "android");
            urlConnection.setRequestProperty("code", code[0]);

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

            Log.i("Corridas","Get Corridas Service : {}"+finalJson);
            Type listType = new TypeToken<MeusEventosResponse>(){}.getType();
            MeusEventosResponse meusEventos = new Gson().fromJson(finalJson.toString(),listType);

            return meusEventos;

        } catch (MalformedURLException e) {
            return  null;
        } catch (IOException e) {
            return  null;
        }
    }
}
