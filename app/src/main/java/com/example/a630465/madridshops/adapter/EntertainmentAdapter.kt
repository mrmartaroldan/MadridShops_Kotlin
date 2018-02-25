package com.example.a630465.madridshops.adapter

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a630465.madridshops.R
import com.example.domain.model.Entertainment
import com.example.domain.model.Entertainments
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class EntertainmentAdapter(val entertainmentsList: Entertainments) : RecyclerView.Adapter<EntertainmentAdapter.EntertainmentViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EntertainmentViewHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        view.setOnClickListener(onClickListener)

        return EntertainmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entertainmentsList.count()
    }

    override fun onBindViewHolder(holder: EntertainmentViewHolder?, position: Int) {
        holder?.bindItem(entertainmentsList.get(position))
    }

    class EntertainmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItem(entertainment: Entertainment){

            itemView.tv_name.text = entertainment.name
            itemView.tv_address.text = entertainment.address

            Picasso.with(itemView.context)
                    .load(entertainment.logo)
                    .placeholder(R.drawable.no_image)
                    .into(itemView.iv_logo)

        }
    }

}

