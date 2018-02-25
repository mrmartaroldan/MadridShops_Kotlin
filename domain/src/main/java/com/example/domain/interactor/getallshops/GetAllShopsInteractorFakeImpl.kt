package com.keepcoding.madridshops.domain.interactor.getallshops

import com.example.domain.model.Entertainment
import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.interactor.ErrorClosure
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessClosure
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.model.Shop
import java.util.*

class GetAllShopsInteractorFakeImpl: GetAllActivitiesInteractor {
    override fun execute(success: SuccessCompletion<Entertainments>, error: ErrorCompletion) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val entertainments = createFakeListOfEntertainments()

            success.successCompletion(entertainments)
        } else {
            error.errorCompletion("Error while accessing the Repository")
        }
    }

    fun execute(success: SuccessClosure<Entertainments>, error: ErrorClosure) {
        var allOk = true

        // connect to the repository

        if (allOk) {
            val entertainments = createFakeListOfEntertainments()

            success(entertainments)
        } else {
            error("Error while accessing the Repository")
        }
    }

    fun createFakeListOfEntertainments(): Entertainments {
        val list = ArrayList<Entertainment>()

        for (i in 0..100) {
            val shop = Shop(i, address = "Shop " + i, name = "Address " + i, logo = "" + i, image = "", latitude = 0.0, longitude = 0.0)
            list.add(shop)
        }

        return Entertainments(list)
    }
}