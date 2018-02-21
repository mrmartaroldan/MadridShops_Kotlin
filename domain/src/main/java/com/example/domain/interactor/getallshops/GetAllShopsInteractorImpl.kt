package com.keepcoding.madridshops.domain.interactor.getallshops

import android.content.Context
import android.util.Log
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.model.Shop
import com.keepcoding.madridshops.domain.model.Shops
import com.keepcoding.madridshops.repository.Repository
import com.keepcoding.madridshops.repository.RepositoryImpl
import com.keepcoding.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference
import java.util.*

class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {
        val tempList = ArrayList<Shop>()
        list.forEach {
            if(it.latitude.length !=0 && it.longitude.length !=0) {
                val shop = Shop(it.id.toInt(),
                        it.name,
                        it.address,
                        it.logo,
                        (it.latitude.replace(",", "").replace(" ", "")).toDouble(),
                        (it.longitude.replace(",", "").replace(" ", "")).toDouble())

                tempList.add(shop)
            }
        }

        val shops = Shops(tempList)
        return shops

    }
}


