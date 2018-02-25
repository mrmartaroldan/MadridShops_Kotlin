package com.keepcoding.madridshops.repository

import com.keepcoding.madridshops.repository.model.ActivityEntity
import com.keepcoding.madridshops.repository.model.ShopEntity

interface Repository {

    fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
}