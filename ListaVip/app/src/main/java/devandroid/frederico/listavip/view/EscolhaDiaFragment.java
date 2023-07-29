package devandroid.frederico.listavip.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Calendar;
import java.util.Locale;

import devandroid.frederico.listavip.R;

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

    private void showDateTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        long currentTimestamp = calendar.getTimeInMillis();

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setSelection(currentTimestamp);

        final MaterialDatePicker<Long> datePicker = builder.build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {

                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.setTimeInMillis(selection);

                showTimePickerForEndDate(selectedCalendar);
            }
        });

        datePicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER_TAG");
    }

    private void showTimePickerForEndDate(Calendar selectedDate) {

        int hour = selectedDate.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDate.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(requireContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String dataSelecionada = String.format(Locale.getDefault(), "%02d/%02d/%d %02d:%02d",
                        selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1,
                        selectedDate.get(Calendar.YEAR), hourOfDay, minute);

            }
        }, hour, minute, true);

        timePickerDialog.show();
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

        ImageButton btnData = rootView.findViewById(R.id.btnData);
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });

        return rootView;

    }
}