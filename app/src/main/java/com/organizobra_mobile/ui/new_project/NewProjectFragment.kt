package com.organizobra_mobile.ui.new_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.organizobra_mobile.R
import com.organizobra_mobile.databinding.FragmentNewProjectBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewProjectFragment : Fragment() {
    private var binding: FragmentNewProjectBinding? = null
    private var periodoObraLayout: TextInputLayout? = null
    private var periodoObraInput: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val newProjectViewModel =
            ViewModelProvider(this).get(NewProjectViewModel::class.java)
        binding = FragmentNewProjectBinding.inflate(inflater, container, false)
        val root: View = binding!!.root


        setDatePickerLayout(root)

        return root
    }

    private fun setDatePickerLayout(root: View) {
        periodoObraLayout = root.findViewById(R.id.periodoObraLayout)
        periodoObraLayout?.setEndIconDrawable(R.drawable.ic_calendar)

        periodoObraInput = root.findViewById(R.id.periodoObraInput)
        periodoObraInput?.setOnClickListener(View.OnClickListener { v: View? ->
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecione o Periodo")
                .build()
            picker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
            picker.addOnPositiveButtonClickListener { selection ->
                val startMillis = selection
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val startDate = sdf.format(Date(startMillis))
                periodoObraInput?.setText("$startDate")
            }
        })
    }


}

