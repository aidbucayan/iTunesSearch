package com.adrian.bucayan.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.bucayan.R
import com.adrian.bucayan.models.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_card_view.view.*
/**
 * @author Adrian Bucayan
 */
class MainResultsAdapter (private var newsList: List<Results>, var callback: (Results) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_card_view, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(newsList[position], callback)
    }

    override fun getItemCount() = newsList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(results: Results, clickListener: (Results) -> Unit) {
            itemView.card_view_trackName.text = results.trackName
            itemView.card_view_price.text = results.trackPrice.toString() + " " + results.currency
            itemView.primaryGenreName.text = results.primaryGenreName
            itemView.card_view_row.setOnClickListener { clickListener(results)}

            Picasso.get().load(results.artworkUrl100)
                .centerCrop().fit()
                .placeholder(R.color.grey)
                .into(itemView.card_view_image)

        }
    }

}
