package com.example.a630465.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.a630465.madridshops.R
import com.example.a630465.madridshops.fragment.ListFragment
import com.example.a630465.madridshops.utils.ACTIVITIES
import com.example.a630465.madridshops.utils.INTENT_GO_TO
import com.example.a630465.madridshops.utils.SHOPS
import com.example.domain.model.Entertainments
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.interactor.getallshops.GetAllActivitiesInteractor
import com.keepcoding.madridshops.domain.interactor.getallshops.GetAllActivitiesInteractorImpl
import com.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.keepcoding.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.keepcoding.madridshops.domain.model.Shop
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity : AppCompatActivity(), ListFragment.OnClickSelectedEntertainment{

    private lateinit var listFragment: ListFragment
    private lateinit var context: Context

    private var map: GoogleMap? = null
    private val GO_TO by lazy { intent.getStringExtra(INTENT_GO_TO) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        listFragment = fragmentManager.findFragmentById(R.id.fragment_list) as ListFragment

        downloadData()
    }

    private fun downloadData() {

        if (GO_TO == SHOPS){

            val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
            getAllShopsInteractor.execute(object: SuccessCompletion<Entertainments> {
                override fun successCompletion(entertainments: Entertainments) {

                    listFragment.setData(entertainments)
                    initializeMap(entertainments)
                }

            }, object: ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {

                    context?.let { Toast.makeText(it,"Fallo de conexión", Toast.LENGTH_LONG).show() }
                    downloadData()
                }
            })

        } else if(GO_TO == ACTIVITIES){

            val getAllActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
            getAllActivitiesInteractor.execute(object: SuccessCompletion<Entertainments> {
                override fun successCompletion(entertainments: Entertainments) {

                    listFragment.setData(entertainments)
                    initializeMap(entertainments)
                }

            }, object: ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {

                    context?.let { Toast.makeText(it,"Fallo de conexión", Toast.LENGTH_LONG).show() }
                    downloadData()
                }
            })

        }

    }

    private fun initializeMap(entertainments: Entertainments) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({ mapa ->

            centerMapInPosition(mapa,40.416775,-3.703790)
            mapa.uiSettings.isRotateGesturesEnabled = false
            mapa.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, mapa)
            map = mapa
            addAllPins(entertainments)
        })
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder().
                target(coordinate).
                zoom(15f).
                build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                    ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)
                    return
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {

            }
        }
    }

    fun addAllPins(entertainments: Entertainments) {
        for (i in 0 until entertainments.count()) {
            val entertainments = entertainments.get(i)

            addPin(this.map !!, entertainments.latitude!!, entertainments.longitude!! , entertainments.name)
        }
    }

    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String) {
        map.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(title))
    }

    override fun showEntertainmentDetail(shop: Shop) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}