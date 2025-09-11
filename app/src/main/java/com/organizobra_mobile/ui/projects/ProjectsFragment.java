package com.organizobra_mobile.ui.projects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.organizobra_mobile.R;
import com.organizobra_mobile.databinding.FragmentProjectsBinding;

import java.util.ArrayList;
import java.util.List;

public class ProjectsFragment extends Fragment {

    private List<ObraCard> obraList = new ArrayList<>();

    private ImageButton addNewProject;

    private FragmentProjectsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ProjectsViewModel homeViewModel =
                new ViewModelProvider(this).get(ProjectsViewModel.class);

        binding = FragmentProjectsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.addNewProject.setOnClickListener(v -> {
            NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.nav_new_project);
        });


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        obraList.add(new ObraCard("Card 1", "Subtitle 1"));
        obraList.add(new ObraCard("Card 2", "Subtitle 2"));
        obraList.add(new ObraCard("Card 3", "Subtitle 3"));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        LinearLayout cardContainer = view.findViewById(R.id.cardContainer);

        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (ObraCard card : obraList) {
            View cardView = inflater.inflate(R.layout.obra_card, cardContainer, false);

            TextView titleView = cardView.findViewById(R.id.obraName);
            TextView subtitleView = cardView.findViewById(R.id.obraAdress);
            ImageButton editButton = cardView.findViewById(R.id.editButton);

            titleView.setText(card.name);
            subtitleView.setText(card.address);

            editButton.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Editing " + card.name, Toast.LENGTH_SHORT).show();
            });

            cardContainer.addView(cardView);

    }

}
    }