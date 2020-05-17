package com.owlsdevelopers.kmpweatherdemo.di


import com.owlsdevelopers.kmpweatherdemo.domain.repository.WeatherRepository
import com.owlsdevelopers.kmpweatherdemo.domain.usecase.GetWeather
import domain.api.WeatherAPI
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = Kodein {

    /**
     * USECASES
     */
    bind<GetWeather>() with singleton { GetWeather(instance()) }

    /**
     * REPOSITORIES
     */
    bind<WeatherRepository>() with provider { WeatherRepository(instance()) }

    /**
     * NETWORK API
     */
    bind<WeatherAPI>() with provider { WeatherAPI() }
}