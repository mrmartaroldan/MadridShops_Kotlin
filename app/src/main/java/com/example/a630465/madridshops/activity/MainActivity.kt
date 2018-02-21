package com.example.a630465.madridshops.activity

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a630465.madridshops.R
import com.joanzapata.iconify.Iconify.with
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.keepcoding.madridshops.domain.interactor.internetstatus.InternetStatusInteractorImpl
import com.keepcoding.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(FontAwesomeModule())
        setContentView(R.layout.activity_main)

        checkConnection()

    }

    private fun checkConnection() {

        InternetStatusInteractorImpl().execute(this, success = {
            enableComponents()

        }, error = {
            AlertDialog.Builder(this)
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_conexión)
                    .setPositiveButton(R.string.reintentar, { dialog, which ->
                        dialog.dismiss()
                        checkConnection()
                    })
                    .setNegativeButton(R.string.salir, { dialog, which ->
                        finish()
                    })
                    .show()
        })
    }

    private fun enableComponents() {
        tv_shops.visibility = View.VISIBLE
        tv_activities.visibility = View.VISIBLE

        tv_shops.setOnClickListener { Router().navigateFromMainActivityToShopsActivity(this) }
        tv_activities.setOnClickListener { Router().navigateFromMainActivityToActivitiesActivity(this) }
    }
}
