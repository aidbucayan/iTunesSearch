package com.adrian.bucayan.utils

import java.text.Collator
import java.util.*
import kotlin.collections.ArrayList


/**
 * @author Adrian Bucayan
 */
object CountryList {

    @JvmStatic
    fun getCountry(): ArrayList<Country> { // A collection to store our country object
        val countries: ArrayList<Country> = ArrayList()
        // Get ISO countries, create Country object and
        // store in the collection.
        val isoCountries: Array<String> = Locale.getISOCountries()
        for (country in isoCountries) {
            val locale = Locale("en", country)
            val iso: String = locale.isO3Country
            val code: String = locale.country
            val name: String = locale.displayCountry
            if ("" != iso && "" != code && "" != name) {
                countries.add(Country(iso, code, name))
            }
        }
        // Sort the country by their name and then display the content
        // of countries collection object.
        Collections.sort(countries, CountryComparator())
        for (country in countries) {
            //println(country)
        }
        return countries
    }

    /**
     * Country pojo class.
     */
    class Country(
        val iso: String,
        val code: String,
        val name: String
    ) {
        override fun toString(): String {
            return iso + " - " + code + " - " + name.toUpperCase()
        }

    }

    /**
     * CountryComparator class.
     */
    private class CountryComparator internal constructor() : Comparator<Country?> {
        private val comparator: Comparator<Any>

        init {
            comparator = Collator.getInstance()
        }

        override fun compare(p0: Country?, p1: Country?): Int {
            return comparator.compare(p0.toString(), p1.toString())
        }
    }
}