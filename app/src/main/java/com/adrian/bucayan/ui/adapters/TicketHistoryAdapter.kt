package com.adrian.bucayan.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.math.RoundingMode


/*
class TicketHistoryAdapter (private var newsList: List<Ticket>, var callback: (Ticket) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_ticket, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(newsList[position], callback)
    }

    override fun getItemCount() = newsList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ticket: Ticket, clickListener: (Ticket) -> Unit) {
            //itemView.row_ticket_holder.setOnClickListener { clickListener(news)}
            var timeDateManager = TimeDateManager()

            itemView.row_ticket_rego.text = ticket.vehicle!!.registration!!.toUpperCase()
            itemView.row_ticket_zone.text = ticket.zone!!.zoneId

            itemView.row_ticket_from.text = timeDateManager.getDateAndTime(ticket.entered!!)
            itemView.row_ticket_expires.text = timeDateManager.getDateAndTime(ticket.exited!!)

            itemView.row_ticket_paid.text = "$" + ticket.paidAmount!!.setScale(2, RoundingMode.HALF_UP).toString()

        }
    }

}*/
