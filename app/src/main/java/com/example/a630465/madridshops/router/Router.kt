package com.keepcoding.madridshops.router

import android.content.Intent
import com.example.a630465.madridshops.activity.MainActivity
import com.example.a630465.madridshops.activity.ShopDetailActivity
import com.example.a630465.madridshops.activity.ListActivity
import com.example.a630465.madridshops.utils.ACTIVITIES
import com.example.a630465.madridshops.utils.INTENT_GO_TO
import com.example.a630465.madridshops.utils.INTENT_SHOP_DETAIL
import com.example.a630465.madridshops.utils.SHOPS
import com.example.domain.model.Entertainment
import com.keepcoding.madridshops.domain.model.Shop

class Router {
    fun navigateFromMainActivityToShopsList(main: MainActivity) {
        main.startActivity(Intent(main, ListActivity::class.java).putExtra(INTENT_GO_TO, SHOPS))
    }

    fun navigateFromMainActivityToActivitiesList(main: MainActivity) {
        main.startActivity(Intent(main, ListActivity::class.java).putExtra(INTENT_GO_TO, ACTIVITIES))
    }

    fun navigateFromShopActivityToShopDetailActivity(listActivity: ListActivity, entertainment: Entertainment){
        listActivity.startActivity(Intent(listActivity, ShopDetailActivity::class.java).putExtra(INTENT_SHOP_DETAIL, entertainment))
    }

}