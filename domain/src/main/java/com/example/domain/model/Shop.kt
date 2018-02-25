package com.keepcoding.madridshops.domain.model

import com.example.domain.model.Entertainment
import java.io.Serializable
import java.util.*

data class Shop(override val id: Int,
                override val name: String,
                override val address: String,
                override val logo: String,
                override val image: String,
                override val latitude: Double,
                override val longitude: Double,
                override val openingHours: String,
                override val description: String): Serializable, Entertainment




class Shops(val shops: MutableList<Shop>): Aggregate<Shop> {

    override fun count(): Int = shops.size

    override fun all(): List<Shop> = shops

    override fun get(position: Int): Shop {
        return shops.get(position)
    }

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }
}