package com.keepcoding.madridshops.domain.interactor.getallshops

import com.example.domain.model.Entertainments
import com.keepcoding.madridshops.domain.interactor.ErrorCompletion
import com.keepcoding.madridshops.domain.interactor.SuccessCompletion


interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<Entertainments>, error: ErrorCompletion)
}