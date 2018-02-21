package com.example.a630465.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.a630465.madridshops.R
import com.keepcoding.madridshops.domain.model.Shop
import com.keepcoding.madridshops.domain.model.Shops
import com.squareup.picasso.Picasso

class ShopAdapter(val shopsList: Shops?) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)

        return ShopViewHolder(view)
    }

    override fun getItemCount(): Int {
        shopsList?.let { return shopsList.count() }
        return 0
    }

    override fun onBindViewHolder(holder: ShopViewHolder?, position: Int) {
        shopsList?.let { holder?.bindItem(shopsList.get(position))  }
    }

    class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image_shop = itemView.findViewById<ImageView>(R.id.iv_logo)
        val name_shop = itemView.findViewById<TextView>(R.id.tv_name)
        val address_shop = itemView.findViewById<TextView>(R.id.tv_address)

        fun bindItem(shop: Shop){

            name_shop.text = shop.name
            Picasso.with(itemView.context)
                    .load(shop.logo)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image_shop)

            address_shop.text = shop.address
        }
    }

}

