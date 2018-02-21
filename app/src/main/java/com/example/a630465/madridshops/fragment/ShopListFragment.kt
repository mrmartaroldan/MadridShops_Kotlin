package com.example.a630465.madridshops.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a630465.madridshops.R
import com.example.a630465.madridshops.adapter.ShopAdapter
import com.keepcoding.madridshops.domain.model.Shops
import kotlinx.android.synthetic.main.fragment_list.view.*


class ShopListFragment : Fragment() {

    private lateinit var root: View
    private lateinit var shopRecyclerView: RecyclerView

    private var adapter: ShopAdapter? = null
    private var shops: Shops? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        inflater?.let {
            root = it.inflate(R.layout.fragment_list, container, false)

            shopRecyclerView = root.findViewById(R.id.recycler_view) as RecyclerView
            shopRecyclerView.layoutManager = GridLayoutManager(activity, 1)
            shopRecyclerView.itemAnimator = DefaultItemAnimator()

            adapter = ShopAdapter(shops)
            shopRecyclerView.adapter = adapter
        }

        return root
    }

    fun setShops(shops: Shops) {
        this.shops = shops

        adapter = ShopAdapter(shops)
        shopRecyclerView.adapter = adapter

    }
}