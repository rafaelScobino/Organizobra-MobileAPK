package com.organizobra_mobile.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.organizobra_mobile.DB.AppState
import com.organizobra_mobile.R
import com.organizobra_mobile.databinding.FragmentPeopleBinding
import com.organizobra_mobile.ui.projects.ObraCard

class PeopleFragment : Fragment() {
    private var binding: FragmentPeopleBinding? = null

    private val funcList: MutableList<FuncCard> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val peopleViewModel =
            ViewModelProvider(this).get(PeopleViewModel::class.java)

        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun mapFunc(){

        for (project in AppState.projCodeList){

            val proj = AppState.projectMap[project]
            for (func in proj!!.employeesList){
                val cardMap = func.getCard()
                println(cardMap)
                    funcList.add(FuncCard(cardMap.name,cardMap.job,cardMap.subtitle,cardMap.project))
            }
            println(proj)


        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.mapFunc()

        val cardContainer = view.findViewById<LinearLayout>(R.id.cardContainer)

        val inflater = LayoutInflater.from(context)

        for (card in funcList) {
            val cardView = inflater.inflate(R.layout.func_card, cardContainer, false)

            val titleView = cardView.findViewById<TextView>(R.id.FuncName)
            val jobView = cardView.findViewById<TextView>(R.id.FuncJob)
            val subtitleView = cardView.findViewById<TextView>(R.id.Subtitle)
            val projCodeView = cardView.findViewById<TextView>(R.id.ProjCode)
            val editButton = cardView.findViewById<ImageButton>(R.id.editButton)

            titleView.text = card.name
            jobView.text = card.job
            subtitleView.text = card.subtitle
            projCodeView.text = card.projCode

            editButton.setOnClickListener { v: View? ->
                Toast.makeText(
                    context, "Editing " + card.name, Toast.LENGTH_SHORT
                ).show()
            }

            cardContainer.addView(cardView)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}