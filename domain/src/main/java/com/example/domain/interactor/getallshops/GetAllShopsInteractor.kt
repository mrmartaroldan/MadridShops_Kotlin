package com.keepcoding.madridshops.domain.interactor.getallshops

import com.example.domain.model.Entertainment
import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion
import com.keepcoding.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Entertainments>, error: ErrorCompletion)
}