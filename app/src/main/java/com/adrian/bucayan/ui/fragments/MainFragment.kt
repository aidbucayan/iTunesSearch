package com.adrian.bucayan.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.adrian.bucayan.R
import com.adrian.bucayan.models.ItunesResponse
import com.adrian.bucayan.models.Results
import com.adrian.bucayan.ui.adapters.MainResultsAdapter
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModel
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModelFactory
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import com.adrian.bucayan.utils.*

/**
 * @author Adrian Bucayan
 */

class MainFragment : BaseFragment() {

    @Inject
    lateinit var mainViewModelFactory: MyViewModelFactory
    lateinit var mainViewModel: MyViewModel

    @Inject
    lateinit var utils: Utils
    @Inject
    lateinit var pref: Pref

    private var mResultList: List<Results> = ArrayList()
    private var selectedISO : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
            MyViewModel::class.java
        )

        mainViewModel.iTunesResponseResult().observe(this, Observer<Response<ItunesResponse>> {
            if (it != null) {
                if (it.isSuccessful) {
                    Timber.e("SUCCESS")

                    val sdf = SimpleDateFormat("d MMMM yyyy, h:mm:ss a")
                    val currentDateAndTime = sdf.format(Date())
                    main_date.text = "Last Updated: $currentDateAndTime"

                    main_swipe.isRefreshing = false
                    mResultList = it.body()?.results!!
                    Timber.e("mTicketList size %s", mResultList.size)
                    main_list.apply {
                        setHasFixedSize(true)
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter = MainResultsAdapter(mResultList) { resultsItem: Results ->
                            itemClicked(resultsItem)
                        }
                    }
                    saveSearchInfo()
                    hideKeyboard()

                } else
                    main_swipe.isRefreshing = false
            }
        })

        mainViewModel.iTunesResponseLoader().observe(this, Observer<Boolean> {

        })

        mainViewModel.iTunesResponseError().observe(this, Observer<String> {
            main_swipe.isRefreshing = false
            activity?.let { it1 -> DialogFactory.showInfoDialog(it1,  "ERROR", it.toString()) }
        })

        main_country_select.setOnClickListener {
            activity?.let { it1 ->
                DialogFactory.showCountry(it1, getString(R.string.select_country), MaterialDialog.ListCallback { _, _, _, text ->
                        CountryList.getCountry().apply {
                            for (i in 0 until CountryList.getCountry().size) {
                                if (this[i].name.equals(text as String?)) {
                                    Timber.e("ISO = %s", this[i].code)
                                    main_country_select.text = this[i].name
                                    selectedISO = this[i].code
                                }
                            }
                        }
                    })
            }
        }

        main_media_select.setOnClickListener {
            activity?.let { it1 -> DialogFactory.showMedia(
                    it1, getString(R.string.select_media),
                    MaterialDialog.ListCallback { _, _, _, text ->
                        main_media_select.text = text
                        if(main_search.text.isNotBlank() &&
                            selectedISO.isNotEmpty() && main_media_select.text.isNotBlank()) {
                            searchApi()
                        }
                    })
            }
        }

        main_search.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchApi()
                true
            }
            false

        }

        main_swipe.setOnRefreshListener { searchApi() }

        if(!pref.PREF_LAST_SEARCH.isNullOrEmpty()) {
            main_search.setText(pref.PREF_LAST_SEARCH)
        }

        if(!pref.PREF_LAST_COUNTRY.isNullOrEmpty()) {
            main_country_select.text = pref.PREF_LAST_COUNTRY
            selectedISO = pref.PREF_LAST_ISO.toString()
        }

        if(!pref.PREF_LAST_MEDIA.isNullOrEmpty()) {
            main_media_select.text = pref.PREF_LAST_MEDIA
        }

        // if all has value auto search
        if(!pref.PREF_LAST_SEARCH.isNullOrEmpty() && !pref.PREF_LAST_COUNTRY.isNullOrEmpty()
            && !pref.PREF_LAST_MEDIA.isNullOrEmpty()) {
            searchApi()
        }
    }

    /**
     * Saving for a pref database
     * */
    private fun saveSearchInfo() {
        pref.PREF_LAST_SEARCH = main_search.text.toString()
        pref.PREF_LAST_COUNTRY =  main_country_select.text.toString()
        pref.PREF_LAST_ISO = selectedISO
        pref.PREF_LAST_MEDIA =  main_media_select.text.toString()
    }

    private fun searchApi() {
        if(utils.isNetworkAvailable()) {
            if(main_search.text.isNotBlank() &&
                selectedISO.isNotEmpty() && main_media_select.text.isNotBlank()) {
                main_swipe.isRefreshing = true
                mainViewModel.search(main_search.text.toString(), selectedISO,
                    main_media_select.text.toString())
            } else {
                main_swipe.isRefreshing = false
                Toast.makeText(activity, "Please provide for search, select the country and media",
                    Toast.LENGTH_LONG).show()
            }

        } else {
            activity?.let { DialogFactory.showInfoDialog(it,  getString(R.string.no_connectivity_title),
                    getString(R.string.no_connectivity_text
                    ))
            }
        }
    }

    private fun itemClicked(result: Results) {
        Timber.e("ItemClicked = %s", result)
        // changing fragment
        val bundle = Bundle()
        bundle.putSerializable(Constants.RESULT, result)
        changeFragment(DetailsFragment(), DetailsFragment().javaClass.simpleName, bundle)
    }

}
