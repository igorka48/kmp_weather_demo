package com.owlsdevelopers.kmpweatherdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.owlsdevelopers.kmpweatherdemo.presentation.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : Fragment(R.layout.weather_fragment) {

    private lateinit var viewModel: WeatherViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.weatherLiveData.addObserver {
            it?.let {
                cityTv.text = getString(R.string.weather_placeholder, it.name)
                dscTv.text = it.weather?.first()?.description
            }
        }
    }

}
