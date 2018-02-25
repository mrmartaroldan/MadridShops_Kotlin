package com.keepcoding.madridshops.domain.interactor.getallshops

import android.content.Context
import com.example.domain.model.Entertainment
import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.model.Shop
import com.keepcoding.madridshops.repository.Repository
import com.keepcoding.madridshops.repository.RepositoryImpl
import com.keepcoding.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference
import java.util.*

class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Entertainments>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Entertainments = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Entertainments {
        val tempList = ArrayList<Entertainment>()
        list.forEach {
            if(it.latitude.length !=0 && it.longitude.length !=0) {
                val shop = Shop(it.id.toInt(),
                        it.name,
                        it.address,
                        it.logo,
                        it.img,
                        (it.latitude.replace(",", "").replace(" ", "")).toDouble(),
                        (it.longitude.replace(",", "").replace(" ", "")).toDouble(),
                        it.openingHours,
                        it.description)

                tempList.add(shop)
            }
        }

        val shops = Entertainments(tempList)
        return shops

    }
}


