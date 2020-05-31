package com.example.app_mobile.ui.classement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ClassementViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ClassementViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ICI C'EST LE CLASSEMENT");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
