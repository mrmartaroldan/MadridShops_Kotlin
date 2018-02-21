package com.keepcoding.madridshops.domain.interactor.internetstatus

import android.content.Context
import android.net.ConnectivityManager
import com.keepcoding.madridshops.domain.interactor.CodeClosure
import com.keepcoding.madridshops.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(context: Context, success: CodeClosure, error: ErrorClosure) {
        val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = connection.activeNetworkInfo

            if(activeNetwork != null && activeNetwork.isConnectedOrConnecting){
                success()
            }else{
                error("Conexión erronea")
            }
    }

}