package com.example.aluno.corridaapp.fragments;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.corridaapp.LoginActivity;
import com.example.aluno.corridaapp.MainActivity;
import com.example.aluno.corridaapp.R;
import com.example.aluno.corridaapp.adapter.CorridaAdapter;
import com.example.aluno.corridaapp.service.BuscarCorridasAbertasServiceTask;
import com.example.aluno.corridaapp.service.BuscarCorridasFinalizadasServiceTask;
import com.example.aluno.corridaapp.service.BuscarMinhasCorridasServiceTask;
import com.example.aluno.corridaapp.service.response.CorridaResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrincipalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spnSituacao;
    private ListView listaCorrida;
    private Button botaoBuscar;
    List<CorridaResponse> lis;

    private OnFragmentInteractionListener mListener;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrincipalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrincipalFragment newInstance(String param1, String param2) {
        PrincipalFragment fragment = new PrincipalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_principal, container,false);
        binding(v);

        preencherLista();

        preencherStatus();

        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mParam1.equalsIgnoreCase("ok")) {
                    if(spnSituacao.getSelectedItemPosition() == 1) {
                        preencherListaAberta();
                    } else if (spnSituacao.getSelectedItemPosition() == 2) {
                        preencherListaFinalizada();
                    }
                }else{
                    Toast.makeText(getContext(), "Você precisa estar logado para realizar está ação.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listaCorrida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!(mParam1.equalsIgnoreCase("ok")) && mParam1 != null){
                    CorridaResponse cr = lis.get(i);
                    if(!(cr.isFinalizada())){
                    int idCr = cr.getId();
                    showFragment(EventosFragment.newInstance(mParam1,String.valueOf(idCr)));
                    }else{
                        Toast.makeText(getContext(), "Está Corrida está finalizada", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Você precisa estar logado para realizar está ação.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void preencherLista(){
        try {
            List<CorridaResponse> lista = new BuscarMinhasCorridasServiceTask().execute().get();

            lis = lista;

            CorridaAdapter adt = new CorridaAdapter(getContext(),lista);

            listaCorrida.setAdapter(adt);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void preencherListaAberta(){
        try {
            List<CorridaResponse> lista = new BuscarCorridasAbertasServiceTask().execute(mParam1).get();

            CorridaAdapter adt = new CorridaAdapter(getContext(),lista);

            listaCorrida.setAdapter(adt);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void preencherListaFinalizada(){
        try {
            List<CorridaResponse> lista = new BuscarCorridasFinalizadasServiceTask().execute(mParam1).get();

            CorridaAdapter adt = new CorridaAdapter(getContext(),lista);

            listaCorrida.setAdapter(adt);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void preencherStatus() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Selecione");
        lista.add("Aberta");
        lista.add("Finalizada");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,lista);

        spnSituacao.setAdapter(adapter);
    }

    private void binding(View v) {
        spnSituacao = v.findViewById(R.id.spnSituacao);
        listaCorrida = v.findViewById(R.id.lstCorridas);
        botaoBuscar = v.findViewById(R.id.btnBusca);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.conteudoframe,fragment).commitAllowingStateLoss();
    }
}
