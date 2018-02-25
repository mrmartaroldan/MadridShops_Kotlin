package com.example.a630465.madridshops.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a630465.madridshops.R
import com.example.a630465.madridshops.adapter.EntertainmentAdapter
import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var root: View
    private lateinit var shopRecyclerView: RecyclerView

    private var entertainments: Entertainments? = null
    private var onClickSelectedEntertainment: OnClickSelectedEntertainment? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            root = inflater!!.inflate(R.layout.fragment_list, container, false)
        return root
    }

    fun setData(entertainments: Entertainments) {

        shopRecyclerView = root.findViewById(R.id.recycler_view) as RecyclerView
        shopRecyclerView.layoutManager = GridLayoutManager(activity, 1)
        shopRecyclerView.itemAnimator = DefaultItemAnimator()

        val adapter = EntertainmentAdapter(entertainments)
        shopRecyclerView.adapter = adapter

        setListenerToAdapter(adapter!!)

    }

    private fun setListenerToAdapter(adapter: EntertainmentAdapter) {
        /*adapter.onClickListener = View.OnClickListener { v: View? ->
            val position = shopRecyclerView.getChildAdapterPosition(v)
            val shop = shops?.get(position)

            onClickSelectedEntertainment?.showEntertainmentDetail(shop!!)
        }*/
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is OnClickSelectedEntertainment) {
            onClickSelectedEntertainment = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        onClickSelectedEntertainment = null
    }

    interface OnClickSelectedEntertainment {
        fun showEntertainmentDetail(shop: Shop)
    }
}