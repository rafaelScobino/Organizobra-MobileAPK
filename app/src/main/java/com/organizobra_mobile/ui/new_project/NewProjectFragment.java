package com.organizobra_mobile.ui.new_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.organizobra_mobile.databinding.FragmentIncludeBinding;
import com.organizobra_mobile.databinding.FragmentNewProjectBinding;


public class NewProjectFragment extends Fragment {

    private FragmentNewProjectBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        NewProjectViewModel newProjectViewModel =
                new ViewModelProvider(this).get(NewProjectViewModel.class);
        binding = FragmentNewProjectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

}
