package com.keepcoding.madridshops.domain.model

import com.example.domain.model.Entertainment
import java.io.Serializable
import java.util.*

data class Activity(override val id: Int,
                override val name: String,
                override val address: String,
                override val logo: String,
                override val image: String,
                override val latitude: Double,
                override val longitude: Double): Serializable, Entertainment




class Activities(val activities: MutableList<Activity>): Aggregate<Activity> {

    override fun count(): Int = activities.size

    override fun all(): List<Activity> = activities

    override fun get(position: Int): Activity {
        return activities.get(position)
    }

    override fun add(element: Activity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }

    override fun delete(element: Activity) {
        activities.remove(element)
    }
}