package com.organizobra_mobile.ui.report;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReportModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReportModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is people fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}