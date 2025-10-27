package com.organizobra_mobile.ui.projects

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
import androidx.navigation.Navigation.findNavController
import com.organizobra_mobile.R
import com.organizobra_mobile.databinding.FragmentProjectsBinding

class ProjectsFragment : Fragment() {
    private val obraList: MutableList<ObraCard> = ArrayList()

    private val addNewProject: ImageButton? = null

    private var binding: FragmentProjectsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val homeViewModel =
            ViewModelProvider(this).get(ProjectsViewModel::class.java)

        binding = FragmentProjectsBinding.inflate(inflater, container, false)
        val root: View = binding!!.root



        binding!!.addNewProject.setOnClickListener { v: View? ->
            val nav = findNavController(requireView())
            nav.navigate(R.id.nav_new_project)
        }


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        obraList.add(ObraCard("Card 1", "Subtitle 1"))
        obraList.add(ObraCard("Card 2", "Subtitle 2"))
        obraList.add(ObraCard("Card 3", "Subtitle 3"))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val cardContainer = view.findViewById<LinearLayout>(R.id.cardContainer)

        val inflater = LayoutInflater.from(context)

        for (card in obraList) {
            val cardView = inflater.inflate(R.layout.obra_card, cardContainer, false)

            val titleView = cardView.findViewById<TextView>(R.id.obraName)
            val subtitleView = cardView.findViewById<TextView>(R.id.obraAdress)
            val editButton = cardView.findViewById<ImageButton>(R.id.editButton)

            titleView.text = card.name
            subtitleView.text = card.address

            editButton.setOnClickListener { v: View? ->
                Toast.makeText(
                    context, "Editing " + card.name, Toast.LENGTH_SHORT
                ).show()
            }

            cardContainer.addView(cardView)
        }
    }
}