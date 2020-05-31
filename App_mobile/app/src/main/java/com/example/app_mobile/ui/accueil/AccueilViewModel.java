package com.example.app_mobile.ui.accueil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccueilViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AccueilViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}