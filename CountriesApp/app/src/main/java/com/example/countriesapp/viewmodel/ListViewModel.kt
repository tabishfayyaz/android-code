package com.example.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countriesapp.di.DaggerApiComponent
import com.example.countriesapp.model.CountriesService
import com.example.countriesapp.model.CountryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//ViewModel doesn't really care or know who receives values from live data (separation of concerns)
class ListViewModel : ViewModel() {

    //LiveData is an object that generates values asynchronously
    //MutableLiveData is an observable whose values we can set
    val countries = MutableLiveData<List<CountryModel>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var countriesService: CountriesService

    fun refresh() {
        fetchCountries()
    }

    /*PRE Retro Fit*/
//    private fun fetchCountries(){
//        val country1 = CountryModel("Albania", "Tirana", "")
//        val country2 = CountryModel("Brazil", "Brasilia", "")
//        val country3 = CountryModel("Czechia", "Praha", "")
//
//        val list = listOf(country1, country2, country3)
//        countries.value = list
//        countryLoadError.value = false
//        loading.value = false
//
//    }

    init {
        DaggerApiComponent.create().inject(this)
    }

    private fun fetchCountries() {
        loading.value = true

        compositeDisposable.add(
//            CountriesService.getCountries()   //PRE-Dagger
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())        //  so communication happens on new thread
                .observeOn(AndroidSchedulers.mainThread())  //  response handled on main thread
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>() {
                    override fun onSuccess(countryModels: List<CountryModel>) {
                        countries.value = countryModels
                        countryLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        //  as the request is happening in background thread we want to dispose the operation in case its not required anymore e.g. user closes the app
        compositeDisposable.clear()
    }
}