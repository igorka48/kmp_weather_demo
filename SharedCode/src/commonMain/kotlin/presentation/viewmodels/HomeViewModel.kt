package com.owlsdevelopers.kmpweatherdemo.presentation.viewmodels
import com.owlsdevelopers.kmpweatherdemo.createApplicationScreenMessage
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class HomeViewModel: ViewModel() {
    private var mLabelLiveData = MutableLiveData("")
    var eventsDispatcher: EventsDispatcher<EventsListener>? = null
    val labelLiveData: LiveData<String>
      get() = mLabelLiveData

    fun buttonPressCommand(){
        //mLabelLiveData.postValue(createApplicationScreenMessage())
        eventsDispatcher?.dispatchEvent { routeToMainPage() }
    }
    interface EventsListener {
        fun routeToMainPage()
    }
}