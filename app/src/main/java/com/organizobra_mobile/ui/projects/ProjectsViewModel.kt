package com.organizobra_mobile.ui.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProjectsViewModel : ViewModel() {
    private val mText = MutableLiveData<String>()

    init {
        mText.value = "This is home fragment"
    }

    val text: LiveData<String>
        get() = mText
}