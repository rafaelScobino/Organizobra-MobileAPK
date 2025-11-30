package com.organizobra_mobile.ui.new_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.organizobra_mobile.DB.AppState
import com.organizobra_mobile.DB.Project
import com.organizobra_mobile.R
import com.organizobra_mobile.databinding.FragmentNewProjectBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewProjectFragment : Fragment() {
    private var binding: FragmentNewProjectBinding? = null

    private lateinit var nomeObra: TextInputEditText
    private lateinit var endereco: TextInputEditText
    private lateinit var cliente: TextInputEditText
    private lateinit var responsavel: TextInputEditText
    private lateinit var periodoObraInput: TextInputEditText
    private lateinit var periodoObraLayout: TextInputLayout
    private lateinit var salvarBtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val newProjectViewModel =
            ViewModelProvider(this).get(NewProjectViewModel::class.java)
        binding = FragmentNewProjectBinding.inflate(inflater, container, false)
        val root: View = binding!!.root


        mapFields(root)
        setDatePicker()
        setSaveButton()
        return root
    }

    private fun mapFields(root: View) {
        nomeObra = root.findViewById(R.id.nomeObraInput)
        endereco = root.findViewById(R.id.enderecoInput)
        cliente = root.findViewById(R.id.clienteInput)
        responsavel = root.findViewById(R.id.responsavelInput)

        periodoObraLayout = root.findViewById(R.id.periodoObraLayout)
        periodoObraInput = root.findViewById(R.id.periodoObraInput)

        salvarBtn = root.findViewById(R.id.imageButton3)
    }

    private fun clearFields() {
        nomeObra.setText("")
        endereco.setText("")
        cliente.setText("")
        responsavel.setText("")
        periodoObraInput.setText("")
    }

    private fun showSavedToast() {
        Toast.makeText(requireContext(), "Projeto salvo!", Toast.LENGTH_SHORT).show()
    }

    private fun setDatePicker() {
        periodoObraLayout.setEndIconDrawable(R.drawable.ic_calendar)

        periodoObraInput.setOnClickListener {
            val picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Selecione a data")
                .build()

            picker.show(parentFragmentManager, "DATE_PICKER")

            picker.addOnPositiveButtonClickListener { millis ->
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formatted = sdf.format(Date(millis))
                periodoObraInput.setText(formatted)
            }
        }
    }


    fun printProject(p: Project) {
        println("------ PROJECT ------")
        println("id: ${p.id}")
        println("code: ${p.code}")
        println("name: ${p.name}")
        println("address: ${p.address}")
        println("customer: ${p.customer}")
        println("manager: ${p.manager}")
        println("beginDate: ${p.beginDate}")

//        println("employeesList:")
//        if (p.employeesList.isEmpty()) {
//            println("   (nenhum funcionário)")
//        } else {
//            p.employeesList.forEach { e ->
//                println("   - id: ${e.id}")
//                println("     nome: ${e.name}")
//                println("     função: ${e.job}")
//                println("     diária: ${e.dailyRate}")
//            }
//        }

        println("---------------------")
    }

    private fun setSaveButton() {
        salvarBtn.setOnClickListener {

            val p = Project(
                name = nomeObra.text.toString(),
                address = endereco.text.toString(),
                customer = cliente.text.toString(),
                manager = responsavel.text.toString(),
                beginDate = periodoObraInput.text.toString()
            )
            AppState.projectMap[p.code] = p
            AppState.projCodeList.add(p.code)

            // feedback simples
            println("Projeto salvo: $p")
            printProject(p)
            AppState.projectMap[p.code]?.let { it1 -> printProject(it1) }

            showSavedToast()

            clearFields()

        }
    }


}

