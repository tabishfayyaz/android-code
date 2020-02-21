package com.example.countriesapp.model

import com.example.countriesapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountriesService @Inject constructor() {

    /* WORKING CODE BEFORE DAGGER INTEGRATION */
//    companion object {
//        private const val BASE_URL = "https://raw.githubusercontent.com/"
//        private fun create(): CountriesApi {
//            val retrofit = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //for rxjava based callback, converts the API response into observable/single or what have you
//                .baseUrl(BASE_URL)
//                .build()
//            return retrofit.create(CountriesApi::class.java)
//        }

//        private val api by lazy {
//            create()
//        }

//        fun getCountries(): Single<List<CountryModel>> {
//            return api.getCountries()
//        }
//    }

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<CountryModel>> {
        return api.getCountries()
    }

    @Inject
    lateinit var api: CountriesApi
}