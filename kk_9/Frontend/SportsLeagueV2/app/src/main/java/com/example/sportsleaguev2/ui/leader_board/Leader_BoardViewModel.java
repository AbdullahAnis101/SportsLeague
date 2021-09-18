package com.example.sportsleaguev2.ui.leader_board;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Leader_BoardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Leader_BoardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}