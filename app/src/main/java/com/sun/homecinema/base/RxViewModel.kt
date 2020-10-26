package com.sun.homecinema.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel : ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    protected val error = MutableLiveData<String>()
    val errorException: LiveData<String>
        get() = error

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
