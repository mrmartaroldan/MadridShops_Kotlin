package com.example.a630465.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.a630465.madridshops.R
import com.example.a630465.madridshops.utils.INTENT_SHOP_DETAIL
import com.keepcoding.madridshops.domain.model.Shop
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shop_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        tv_name.text = shop.name
        Picasso.with(this)
                .load(shop.image)
                .into(iv_image)
    }

}