package com.example.app_mobile.ui.carte;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CarteViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CarteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ICI C'EST LA CARTE");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

