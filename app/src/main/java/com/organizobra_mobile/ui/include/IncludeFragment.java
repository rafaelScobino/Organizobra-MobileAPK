package com.organizobra_mobile.ui.include;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.organizobra_mobile.R;
import com.organizobra_mobile.databinding.FragmentIncludeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class IncludeFragment extends Fragment {
    private FragmentIncludeBinding binding;
    private AutoCompleteTextView autoCompleteTextView;
    private TextInputEditText dateRangeInput;
    private TextInputLayout datePickerLayout;
    private TextInputEditText valorDiariaInput;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IncludeViewModel includeViewModel =
                new ViewModelProvider(this).get(IncludeViewModel.class);

        binding = FragmentIncludeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Currency input
        setCurrencyInput(root);

        // DatePicker de periodo trabalhado
        setDatePickerLayout(root);

        // Select de obras, MOCKADO
        setObraSelect(root);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setCurrencyInput(View root){
        valorDiariaInput = root.findViewById(R.id.valorDiariaInput);
        valorDiariaInput.addTextChangedListener(new TextWatcher() {

            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    valorDiariaInput.removeTextChangedListener(this);

                    // Remove everything except digits
                    String cleanString = s.toString().replaceAll("[^\\d]", "");

                    if (!cleanString.isEmpty()) {
                        double parsed = Double.parseDouble(cleanString) / 100.0;

                        // Format as currency (Brazil in this example)
                        String formatted = NumberFormat
                                .getCurrencyInstance(new Locale("pt", "BR"))
                                .format(parsed);

                        current = formatted;
                        valorDiariaInput.setText(formatted);
                        valorDiariaInput.setSelection(formatted.length());
                    }

                    valorDiariaInput.addTextChangedListener(this);
                }
            }
        });
    }

    private void setDatePickerLayout(View root){
        datePickerLayout = root.findViewById(R.id.dateRangeLayout);
        datePickerLayout.setEndIconDrawable(R.drawable.ic_calendar);

        dateRangeInput = root.findViewById(R.id.dateRangeInput);
        dateRangeInput.setOnClickListener(v -> {
            MaterialDatePicker<Pair<Long, Long>> picker = MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Selecione o Periodo")
                    .build();

            picker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");

            picker.addOnPositiveButtonClickListener(selection -> {
                Long startMillis = selection.first;
                Long endMillis = selection.second;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String startDate = sdf.format(new Date(startMillis));
                String endDate = sdf.format(new Date(endMillis));

                dateRangeInput.setText(startDate + " - " + endDate);
            });
        });
    }

    private void setObraSelect(View root){
        autoCompleteTextView = root.findViewById(R.id.autoCompleteTextView);
        String[] obras = {"Obra A", "Obra B", "Obra C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                obras
        );
        autoCompleteTextView.setAdapter(adapter);
    }

}