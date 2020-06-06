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
        

        if(SaveSharedPreference.getUserName(getActivity().getBaseContext()).length() != 0)
        {
            /*ViewGroup layout = (ViewGroup) connexion.getParent();
            if(null!=layout) //for safety only  as you are doing onClick
                layout.removeView(connexion);
                layout.removeView(inscription);*/
        }


        return inflatedView;
    }



}




