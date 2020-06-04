package com.example.app_mobile.ui.ajout_photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_mobile.R;
import com.example.app_mobile.ui.connexion.Connexion;

public class PhotoFragment extends Fragment {

    private PhotoViewModel photoViewModel;
    private Object Bundle;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, final Bundle savedInstanceState) {
        photoViewModel =
                ViewModelProviders.of(this).get(PhotoViewModel.class);
        final View photo = inflater.inflate(R.layout.fragment_ajoutphoto, container, false);
        final TextView textView = photo.findViewById(R.id.text_photo);
        photoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        View view = inflater.inflate(R.layout.fragment_ajoutphoto,
                container, false);
        Button button = (Button) view.findViewById(R.id.nav_connexion);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Connexion.class);
                startActivity(intent);
            }
        });
        return photo;
    }
}



