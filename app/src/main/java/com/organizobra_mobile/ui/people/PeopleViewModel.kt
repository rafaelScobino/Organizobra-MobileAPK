package com.organizobra_mobile.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeopleViewModel : ViewModel() {
    private val mText = MutableLiveData<String>()

    init {
        mText.value = "This is people fragment"
    }

    val text: LiveData<String>
        get() = mText
}