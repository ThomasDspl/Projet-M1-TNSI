package com.example.app_mobile.ui.ajout_photo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhotoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PhotoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ICI C'EST LA PHOTO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}