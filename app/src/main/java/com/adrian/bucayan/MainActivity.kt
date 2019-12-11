package com.adrian.bucayan

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.bucayan.models.ItunesResponse
import com.adrian.bucayan.models.Results
import com.adrian.bucayan.ui.activities.BaseActivity
import com.adrian.bucayan.ui.adapters.MainResultsAdapter
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModel
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModelFactory
import com.adrian.bucayan.utils.CountryList
import com.adrian.bucayan.utils.DialogFactory
import com.adrian.bucayan.utils.Pref
import com.adrian.bucayan.utils.Utils
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */


class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModelFactory: MyViewModelFactory
    lateinit var mainViewModel: MyViewModel

    @Inject
    lateinit var utils: Utils
    @Inject
    lateinit var pref: Pref

    private var mResultList: List<Results> = ArrayList()

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MyViewModel::class.java)


        mainViewModel.iTunesResponseResult().observe(this, Observer<Response<ItunesResponse>> {
            if(it != null) {
                if(it.isSuccessful) {
                    Timber.e("SUCCESS")

                    val sdf = SimpleDateFormat("d MMMM yyyy, h:mm:ss a")
                    val currentDateAndTime = sdf.format(Date())
                    main_date.text = "Last Updated: $currentDateAndTime"

                    main_swipe.isRefreshing = false
                    mResultList = it.body()?.results!!
                    Timber.e("mTicketList size %s", mResultList.size)
                    main_list.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        adapter = MainResultsAdapter(mResultList) { eventsItem : Results -> itemClicked(eventsItem) }
                    }
                }
            }
        })

        if(utils.isNetworkAvailable()) {
            mainViewModel.search()
        } else {
            DialogFactory.showInfoDialog(this,  getString(R.string.no_connectivity_title), getString(R.string.no_connectivity_text))
        }

        main_country_select.setOnClickListener{
            DialogFactory.showCountry(this, "Select Country", MaterialDialog.ListCallback { _, _, _, text ->
                CountryList.getCountry().apply {
                    for (i in 0 until CountryList.getCountry().size) {
                        if(this[i].name.equals(text as String?)) {
                            Timber.e("ISO = " + this[i].iso)
                            main_country_select.text = this[i].name
                        }
                    }
                }
            })
        }



    }

    private
    fun itemClicked(result: Results) {
        Timber.e("ItemClicked = %s", result)
    }

}
