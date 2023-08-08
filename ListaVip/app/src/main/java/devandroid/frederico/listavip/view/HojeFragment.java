package devandroid.frederico.listavip.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.controller.PessoaAdapter;
import devandroid.frederico.listavip.database.ListaVipDB;
import devandroid.frederico.listavip.model.Pessoa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HojeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HojeFragment extends Fragment implements PessoaAdapter.OnEditClickListener {

    @Override
    public void onEditClick(int pessoaId) {
        Intent intent = new Intent(requireContext(), SpinnerActivity.class);
        intent.putExtra("pessoaId", pessoaId);
        startActivity(intent);
    }

    private RecyclerView recyclerView;
    private ListaVipDB listaVipDB;
    private PessoaAdapter adapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HojeFragment() {
    }

    public static HojeFragment newInstance(String param1, String param2) {
        HojeFragment fragment = new HojeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hoje , container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listaVipDB = new ListaVipDB(getActivity());

        List<Pessoa> pessoaList = listaVipDB.listarDadosHoje();


        adapter = new PessoaAdapter(pessoaList);
        adapter.setOnEditClickListener(this);
        adapter.setOnDeleteClickListener(new PessoaAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Pessoa pessoa) {
                listaVipDB.deletarObjeto(pessoa);
                pessoaList.remove(pessoa);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);

        return rootView;

    }

}