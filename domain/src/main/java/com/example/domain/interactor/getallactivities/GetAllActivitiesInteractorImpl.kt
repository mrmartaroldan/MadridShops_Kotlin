package com.keepcoding.madridshops.domain.interactor.getallshops

import android.content.Context
import com.example.domain.model.Entertainment
import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.model.Activity
import com.keepcoding.madridshops.domain.model.Shop
import com.keepcoding.madridshops.repository.Repository
import com.keepcoding.madridshops.repository.RepositoryImpl
import com.keepcoding.madridshops.repository.model.ActivityEntity
import com.keepcoding.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference
import java.util.*

class GetAllActivitiesInteractorImpl(context: Context) : GetAllActivitiesInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Entertainments>, error: ErrorCompletion) {
        repository.getAllActivities(success = {
            val activities: Entertainments = entityMapper(it)
            success.successCompletion(activities)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Entertainments {
        val tempList = ArrayList<Entertainment>()
        list.forEach {
            if(it.latitude.length !=0 && it.longitude.length !=0) {
                val activity = Activity(it.id.toInt(),
                        it.name,
                        it.address,
                        it.logo,
                        it.img,
                        (it.latitude.replace(",", "").replace(" ", "")).toDouble(),
                        (it.longitude.replace(",", "").replace(" ", "")).toDouble(),
                        it.openingHours,
                        it.description)

                tempList.add(activity)
            }
        }

        val activities = Entertainments(tempList)
        return activities

    }
}


