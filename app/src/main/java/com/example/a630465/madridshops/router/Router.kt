package com.keepcoding.madridshops.router

import android.content.Intent
import com.example.a630465.madridshops.activity.MainActivity
import com.example.a630465.madridshops.activity.DetailActivity
import com.example.a630465.madridshops.activity.ListActivity
import com.example.a630465.madridshops.utils.*
import com.example.domain.model.Entertainment

class Router {
    fun navigateFromMainActivityToShopsList(main: MainActivity) {
        main.startActivity(Intent(main, ListActivity::class.java).putExtra(INTENT_GO_TO, SHOPS))
    }

    fun navigateFromMainActivityToActivitiesList(main: MainActivity) {
        main.startActivity(Intent(main, ListActivity::class.java).putExtra(INTENT_GO_TO, ACTIVITIES))
    }

    fun showDetailEntertainment(listActivity: ListActivity, entertainment: Entertainment){
        listActivity.startActivity(Intent(listActivity, DetailActivity::class.java).putExtra(INTENT_DETAIL, entertainment))
    }

}