package com.example.countriesapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.R
import com.example.countriesapp.model.CountryModel
import com.example.countriesapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel

    var adapter = CountryListAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //activity is transient and view model has a larger lifecycle than activity so ViewModelProvider will take care of providing the correct object and not necessarily a new one every time
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countriesList.layoutManager = LinearLayoutManager(this)
        countriesList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            swipeRefreshLayout.isRefreshing = false
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.countries.observe(this, object : Observer<List<CountryModel>> {
            override fun onChanged(countryModels: List<CountryModel>?) {
                if (countryModels != null) {
                    countriesList.visibility = View.VISIBLE
                    adapter.updateCountries(countryModels)
                }
            }
        })

        viewModel.countryLoadError.observe(this, object : Observer<Boolean> {
            override fun onChanged(isError: Boolean?) {
                isError?.let {
                    when {
                        isError -> list_error.visibility = View.VISIBLE
                        else -> list_error.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.loading.observe(this, object : Observer<Boolean> {
            override fun onChanged(isLoading: Boolean?) {
                isLoading?.let {
                    when {
                        isLoading -> loading_view.visibility = View.VISIBLE
                        else -> loading_view.visibility = View.GONE
                    }
                    when {
                        isLoading -> {
                            list_error.visibility = View.GONE
                            countriesList.visibility = View.GONE
                        }
                    }
                }
            }
        })
    }
}
