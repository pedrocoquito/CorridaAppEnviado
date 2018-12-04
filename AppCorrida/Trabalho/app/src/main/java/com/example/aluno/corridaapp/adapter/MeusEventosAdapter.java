package com.example.aluno.corridaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aluno.corridaapp.R;
import com.example.aluno.corridaapp.service.response.CorridaMeusEventosResponse;
import com.example.aluno.corridaapp.service.response.CorridaResponse;
import com.example.aluno.corridaapp.service.response.MeusEventosResponse;

import java.util.List;
import java.util.zip.Inflater;

public class MeusEventosAdapter extends BaseAdapter {

    private List<CorridaMeusEventosResponse> listaAdapter;
    Inflater inflate;
    Context context;

    public MeusEventosAdapter(Context context,List<CorridaMeusEventosResponse> listaAdapter) {
        this.listaAdapter = listaAdapter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAdapter.size();
    }

    @Override
    public CorridaMeusEventosResponse getItem(int i) {
        return listaAdapter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflate == null){
            view = LayoutInflater.from(context).inflate(R.layout.meus_eventos_corrida,null);
        }

        TextView nome = view.findViewById(R.id.meusEventosNome);
        TextView status = view.findViewById(R.id.meusEventosStatus);
        TextView data = view.findViewById(R.id.meusEventosData);
        TextView posicao = view.findViewById(R.id.meusEventosPosicao);
        TextView km = view.findViewById(R.id.meusEventosKm);

        CorridaMeusEventosResponse cr = getItem(i);
        nome.setText("Nome: "+cr.getNome());
        if(cr.isCancelada()){
            status.setText("Status: Cancelada");
        }else{
            status.setText("Status: "+((cr.isFinalizada())?"Finalizada":"Aberta"));
        }
        data.setText("Data: "+cr.getDataCorrida());
        km.setText("KM: "+cr.getKm());
        if(cr.getPosicao()!=0){
            posicao.setText("Posição: "+cr.getPosicao());
        }else{
            posicao.setText("");
        }

        return view;
    }
}
