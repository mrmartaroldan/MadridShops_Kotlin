package com.example.a630465.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.a630465.madridshops.R
import com.example.a630465.madridshops.utils.INTENT_DETAIL
import com.example.domain.model.Entertainment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){

    private lateinit var entertainment: Entertainment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        entertainment = intent.getSerializableExtra(INTENT_DETAIL) as Entertainment

        loadDetails()
    }

    private fun loadDetails() {

        tv_name.text = entertainment.name

        Picasso.with(this)
                .load(entertainment.image)
                .into(iv_image)

        tv_description.text = entertainment.description
        tv_address.text = entertainment.address
        tv_opening_hours.text = entertainment.openingHours
    }

}