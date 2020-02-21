package com.example.countriesapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.R
import com.example.countriesapp.model.CountryModel
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(private var countries: MutableList<CountryModel>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: List<CountryModel>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries.get(position))
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(country: CountryModel) {
            itemView.name.text = country.countryName
            itemView.capital.text = country.capital
            Util.loadImage(itemView.imageView, country.flag, Util.getProgressDrawable(itemView.imageView.context))
        }
    }
}