package com.adrian.bucayan.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrian.bucayan.models.Results
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModel
import com.adrian.bucayan.ui.viewmodels.authentication.MyViewModelFactory
import timber.log.Timber
import javax.inject.Inject
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.adrian.bucayan.R
import com.adrian.bucayan.utils.Constants
import com.adrian.bucayan.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*


/**
 * @author Adrian Bucayan
 */
class DetailsFragment : BaseFragment() {

    private lateinit var results: Results

    @Inject
    lateinit var utils: Utils
    private var mActivity: AppCompatActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("ResourceType", "SetTextI18n", "BinaryOperationInTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        toolbar.title = ""

        mActivity = activity as AppCompatActivity
        mActivity!!.setSupportActionBar(toolbar)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)

        val b = arguments
        if (b != null) {
            Timber.e("BUNDLE RESULT = "+ b.getSerializable(Constants.RESULT))
            results = b.getSerializable(Constants.RESULT) as Results

            Picasso.get().load(results.artworkUrl100)
                .centerCrop().fit()
                .placeholder(R.color.grey)
                .into(details_image)

            toolbarTitle.text = results.primaryGenreName
            details_trackName.text = results.trackName
            details_artistName.text = results.artistName
            details_collectionName.text = results.collectionName
            details_trackPrice.text = results.trackPrice.toString() + " " + results.currency
            details_country.text = results.country
            details_link.text = results.trackViewUrl
        }

        // Open of external link via webview
        details_link.setOnClickListener {
            results.trackViewUrl?.let { it1 -> activity?.let { it2 -> utils.openUrl(it2, it1) } }
        }
    }


}
