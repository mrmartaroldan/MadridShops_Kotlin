package com.example.domain.model

import com.keepcoding.madridshops.domain.model.Aggregate
import java.io.Serializable

interface Entertainment: Serializable {
    val id: Int
    val name: String
    val address: String
    val logo: String
    val image: String
    val latitude: Double
    val longitude: Double
}


class Entertainments(private val entertainments: MutableList<Entertainment>) : Aggregate<Entertainment>{
    override fun count(): Int {
        return entertainments.count()
    }

    override fun all(): List<Entertainment> {
        return entertainments
    }

    override fun get(position: Int): Entertainment {
        return entertainments[position]
    }

    override fun add(element: Entertainment) {
        entertainments.add(element)
    }

    override fun delete(position: Int) {

        if(position > 0 && position < entertainments.size){
            entertainments.removeAt(position)
        }
    }

    override fun delete(element: Entertainment) {
        entertainments.remove(element)
    }

}