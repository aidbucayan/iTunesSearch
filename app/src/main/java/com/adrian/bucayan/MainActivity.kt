package com.adrian.bucayan


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.adrian.bucayan.ui.activities.BaseActivity
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModel
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModelFactory
import javax.inject.Inject

/**
 * @author Adrian Bucayan
 */

class MainActivity : BaseActivity() {

    @Inject
    lateinit var mainViewModelFactory: MyViewModelFactory
    lateinit var mainViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MyViewModel::class.java)


        /*mainViewModel.iTunesResponseResult().observe(this, Observer<Response<ItunesResponse>> {
            if(it != null) {
                if(it.isSuccessful) {
                    Timber.e("SUCCESS")
                }
            }
        })*/

    }

}
