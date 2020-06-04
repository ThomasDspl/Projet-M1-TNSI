package com.example.app_mobile.ui.connexion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConnexionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConnexionViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}
