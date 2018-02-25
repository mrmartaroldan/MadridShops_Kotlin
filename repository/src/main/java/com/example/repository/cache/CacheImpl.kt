package com.keepcoding.madridshops.repository.cache

import android.content.Context
import com.keepcoding.madridshops.repository.db.DBHelper
import com.keepcoding.madridshops.repository.db.build
import com.keepcoding.madridshops.repository.db.dao.ActivityDAO
import com.keepcoding.madridshops.repository.db.dao.ShopDAO
import com.keepcoding.madridshops.repository.model.ActivityEntity
import com.keepcoding.madridshops.repository.model.ShopEntity
import com.keepcoding.madridshops.repository.thread.DispatchOnMainTread
import java.lang.ref.WeakReference

internal class CacheImpl(context: Context): Cache {

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = ShopDAO(cacheDBHelper()).query()
            DispatchOnMainTread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun getAllActivities(success: (activities: List<ActivityEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var activities = ActivityDAO(cacheDBHelper()).query()
            DispatchOnMainTread(Runnable {
                if (activities.count() > 0) {
                    success(activities)
                } else {
                    error("No activities")
                }
            })
        }).run()
    }

    override fun saveAllShops(shops: List<ShopEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainTread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainTread(Runnable {
                    error("Error inserting shops")
                })
            }
        }).run()
    }

    override fun saveAllActivities(activities: List<ActivityEntity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                activities.forEach { ActivityDAO(cacheDBHelper()).insert(it) }

                DispatchOnMainTread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainTread(Runnable {
                    error("Error inserting activities")
                })
            }
        }).run()
    }

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()
            DispatchOnMainTread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()
    }

    override fun deleteAllActivities(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ActivityDAO(cacheDBHelper()).deleteAll()
            DispatchOnMainTread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()
    }

    val context = WeakReference<Context>(context)

    private fun cacheDBHelper(): DBHelper {
        return build(context.get() !!, "MadridShops.sqlite", 1)
    }
}