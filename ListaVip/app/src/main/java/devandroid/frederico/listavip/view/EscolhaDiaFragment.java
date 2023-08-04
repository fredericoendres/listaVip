package devandroid.frederico.listavip.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devandroid.frederico.listavip.R;
import devandroid.frederico.listavip.database.ListaVipDB;
import devandroid.frederico.listavip.model.Pessoa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EscolhaDiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EscolhaDiaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private PessoaAdapter adapter;
    private ListaVipDB listaVipDB;

    private Calendar selectedInicioDate;
    private Calendar selectedFimDate;

    private void showDateTimePickerDialog(boolean isInicio) {
        Calendar calendar = Calendar.getInstance();
        long currentTimestamp = calendar.getTimeInMillis();

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setSelection(currentTimestamp);

        builder.setCalendarConstraints(new com.google.android.material.datepicker.CalendarConstraints.Builder().setOpenAt(currentTimestamp)
                .build());

        final MaterialDatePicker<Long> datePicker = builder.build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.setTimeInMillis(selection);

                List<Pessoa> pessoaList = null;
                if (isInicio) {
                    selectedInicioDate = selectedCalendar;
                    showDateTimePickerDialog(false);
                } else {
                    selectedFimDate = selectedCalendar;
                    pessoaList = listaVipDB.listarDadosData(selectedInicioDate, selectedFimDate);
                    adapter.updateData(pessoaList);
                }

                adapter.updateData(pessoaList);
            }
        });

        datePicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER_TAG");
    }


    public EscolhaDiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EscolhaDiaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EscolhaDiaFragment newInstance(String param1, String param2) {
        EscolhaDiaFragment fragment = new EscolhaDiaFragment();
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


        View rootView = inflater.inflate(R.layout.fragment_escolha_dia, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaVipDB = new ListaVipDB(requireContext());
        ImageButton btnDataInicio = rootView.findViewById(R.id.btnDataInicio);
        ImageButton btnDataFim = rootView.findViewById(R.id.btnDataFim);

        btnDataInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog(true);
            }
        });

        btnDataFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedInicioDate != null) {
                    showDateTimePickerDialog(false);
                } else {
                    Toast.makeText(requireContext(), "Selecione a data inicial antes", Toast.LENGTH_SHORT).show();
                }
            }
        });


                List<Pessoa> pessoaList = new ArrayList<>();
        adapter = new PessoaAdapter(pessoaList);

        adapter = new PessoaAdapter(pessoaList, new PessoaAdapter.OnDeleteClickListener() {
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