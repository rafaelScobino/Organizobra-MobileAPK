package com.organizobra_mobile.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportModel : ViewModel() {
    private val mText = MutableLiveData<String>()

    init {
        mText.value = "This is people fragment"
    }

    val text: LiveData<String>
        get() = mText
}