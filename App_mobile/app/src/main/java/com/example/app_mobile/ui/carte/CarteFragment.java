package com.example.app_mobile.ui.carte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_mobile.R;

public class CarteFragment extends Fragment {
    private CarteViewModel carteViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carteViewModel =
                ViewModelProviders.of(this).get(CarteViewModel.class);
        View carte = inflater.inflate(R.layout.fragment_carte, container, false);
        final TextView textView = carte.findViewById(R.id.text_carte);
        carteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return carte;
    }
}

