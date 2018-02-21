package com.keepcoding.madridshops.router

import android.content.Intent
import com.example.a630465.madridshops.activity.ActvitiesActivity
import com.example.a630465.madridshops.activity.MainActivity
import com.example.a630465.madridshops.activity.ShopsActivity

class Router {
    fun navigateFromMainActivityToShopsActivity(main: MainActivity) {
        main.startActivity(Intent(main, ShopsActivity::class.java))
    }

    fun navigateFromMainActivityToActivitiesActivity(main: MainActivity) {
        main.startActivity(Intent(main, ActvitiesActivity::class.java))
    }

}