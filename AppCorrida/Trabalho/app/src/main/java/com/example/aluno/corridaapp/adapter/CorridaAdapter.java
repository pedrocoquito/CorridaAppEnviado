package com.example.aluno.corridaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aluno.corridaapp.R;
import com.example.aluno.corridaapp.service.response.CorridaResponse;

import java.util.List;
import java.util.zip.Inflater;

public class CorridaAdapter extends BaseAdapter {

    private List<CorridaResponse> listaAdapter;
    Inflater inflate;
    Context context;

    public CorridaAdapter(Context context,List<CorridaResponse> listaAdapter) {
        this.listaAdapter = listaAdapter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAdapter.size();
    }

    @Override
    public CorridaResponse getItem(int i) {
        return listaAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflate == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_corrida,null);
        }

        TextView tvNome = view.findViewById(R.id.ListaCorridaNome);
        TextView tvStatus = view.findViewById(R.id.ListaCorridaStatus);
        TextView tvData = view.findViewById(R.id.ListaCorridaData);
        TextView tvNumInscritos = view.findViewById(R.id.ListaCorridaNumeroInscritos);

        CorridaResponse cr = getItem(i);
        tvNome.setText("Nome: "+cr.getNome());
        tvStatus.setText("Status: "+((cr.isFinalizada())?"Finalizada":"Aberta"));
        tvData.setText("Data: "+cr.getDataCorrida());
        tvNumInscritos.setText("Participantes: "+cr.getNumroInscritos());

        return view;
    }
}
