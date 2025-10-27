package com.organizobra_mobile.ui.include

import android.icu.text.NumberFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.organizobra_mobile.R
import com.organizobra_mobile.databinding.FragmentIncludeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IncludeFragment : Fragment() {
    private var binding: FragmentIncludeBinding? = null
    private var autoCompleteTextView: AutoCompleteTextView? = null
    private var dateRangeInput: TextInputEditText? = null
    private var datePickerLayout: TextInputLayout? = null
    private var valorDiariaInput: TextInputEditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val includeViewModel =
            ViewModelProvider(this).get(IncludeViewModel::class.java)

        binding = FragmentIncludeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        //Currency input
        setCurrencyInput(root)

        // DatePicker de periodo trabalhado
        setDatePickerLayout(root)

        // Select de obras, MOCKADO
        setObraSelect(root)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setCurrencyInput(root: View) {
        valorDiariaInput = root.findViewById(R.id.valorDiariaInput)
        valorDiariaInput?.addTextChangedListener(object : TextWatcher {
            private var current = ""

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (s.toString() != current) {
                    valorDiariaInput?.removeTextChangedListener(this)

                    // Remove everything except digits
                    val cleanString = s.toString().replace("[^\\d]".toRegex(), "")

                    if (!cleanString.isEmpty()) {
                        val parsed = cleanString.toDouble() / 100.0

                        // Format as currency (Brazil in this example)
                        val formatted = NumberFormat
                            .getCurrencyInstance(Locale("pt", "BR"))
                            .format(parsed)

                        current = formatted
                        valorDiariaInput?.setText(formatted)
                        valorDiariaInput?.setSelection(formatted.length)
                    }

                    valorDiariaInput?.addTextChangedListener(this)
                }
            }
        })
    }

    private fun setDatePickerLayout(root: View) {
        datePickerLayout = root.findViewById(R.id.dateRangeLayout)
        datePickerLayout?.setEndIconDrawable(R.drawable.ic_calendar)

        dateRangeInput = root.findViewById(R.id.dateRangeInput)
        dateRangeInput?.setOnClickListener(View.OnClickListener { v: View? ->
            val picker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Selecione o Periodo")
                .build()
            picker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
            picker.addOnPositiveButtonClickListener { selection: Pair<Long, Long> ->
                val startMillis = selection.first
                val endMillis = selection.second

                val sdf =
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val startDate = sdf.format(Date(startMillis))
                val endDate = sdf.format(Date(endMillis))
                dateRangeInput?.setText("$startDate - $endDate")
            }
        })
    }

    private fun setObraSelect(root: View) {
        autoCompleteTextView = root.findViewById(R.id.autoCompleteTextView)
        val obras = arrayOf("Obra A", "Obra B", "Obra C")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            obras
        )
        autoCompleteTextView?.setAdapter(adapter)
    }
}