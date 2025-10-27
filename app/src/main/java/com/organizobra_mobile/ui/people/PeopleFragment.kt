package com.organizobra_mobile.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.organizobra_mobile.databinding.FragmentPeopleBinding

class PeopleFragment : Fragment() {
    private var binding: FragmentPeopleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val peopleViewModel =
            ViewModelProvider(this).get(PeopleViewModel::class.java)

        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        val textView = binding!!.textGallery
        peopleViewModel.text.observe(
            viewLifecycleOwner
        ) { text: String? ->
            textView.text =
                text
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}