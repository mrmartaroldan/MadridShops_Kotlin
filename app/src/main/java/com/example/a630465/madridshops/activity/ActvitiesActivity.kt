package com.example.a630465.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.a630465.madridshops.R
import kotlinx.android.synthetic.main.activity_shops.*

class ActvitiesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)
        setSupportActionBar(toolbar)
    }
}