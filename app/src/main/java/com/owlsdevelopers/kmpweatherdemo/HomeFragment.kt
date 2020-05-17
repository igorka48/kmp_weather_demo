package com.owlsdevelopers.kmpweatherdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.owlsdevelopers.kmpweatherdemo.presentation.viewmodels.HomeViewModel
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment(R.layout.home_fragment), HomeViewModel.EventsListener {

    private lateinit var viewModel: HomeViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.eventsDispatcher = eventsDispatcherOnMain()
        viewModel.eventsDispatcher?.bind(this, this as HomeViewModel.EventsListener)
        viewModel.labelLiveData.addObserver {
            hintTv.text = it
        }
        getWeatherButton.setOnClickListener {
           viewModel.buttonPressCommand()
        }
    }


    override fun routeToMainPage() {
        findNavController().navigate(R.id.action_homeFragment_to_weatherFragment)
    }

}
