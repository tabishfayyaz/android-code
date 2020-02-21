package com.example.countriesapp.di

import com.example.countriesapp.model.CountriesService
import com.example.countriesapp.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])   //which modules this component manages
interface ApiComponent {

    //what this means is that you be able to inject fields inside the object passed as parameter
    fun inject(countriesService: CountriesService)

    fun inject(listViewModel: ListViewModel)
}