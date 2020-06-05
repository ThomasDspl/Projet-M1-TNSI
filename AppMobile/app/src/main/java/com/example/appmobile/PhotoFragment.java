package com.example.appmobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmobile.ui.main.PageViewModel;
import com.example.appmobile.ui.main.SectionsPagerAdapter;

public class PhotoFragment extends Fragment {
    Button connexion = null;
    Button inscription=null;
    Activity activity;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.fragment_photo, container, false);
        connexion = (Button) inflatedView.findViewById(R.id.btn_connexion);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupConnexion();
            }


        });
        inscription = (Button) inflatedView.findViewById(R.id.btn_inscription);

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }


        });



        return inflatedView;
    }
    private void PopupConnexion(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setMessage("Pseudo :");
        EditText input = new EditText(getActivity());
        alert.setView(input);

        alert.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Title");
                alert.setMessage("Mot de passe");
                final EditText input = new EditText(getActivity());
                alert.setView(input);

                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });
                alert.show();


            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }
    private void PopupConnexion2(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("Email :");
        EditText input = new EditText(getActivity());
        alert.setView(input);

        alert.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        alert.setMessage("Pseudo :");
                        EditText input = new EditText(getActivity());
                        alert.setView(input);

                        alert.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                                alert.setTitle("Title");
                                alert.setMessage("Mot de passe");
                                final EditText input = new EditText(getActivity());
                                alert.setView(input);

                                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {


                                    }
                                });
                                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        // Canceled.
                                    }
                                });
                                alert.show();


                            }
                        });
                        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                            }
                        });

                        alert.show();
                    }
                });

            alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }
}




