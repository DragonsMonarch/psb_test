package com.dragonslotos.psb_test.domain.repository

import com.dragonslotos.psb_test.domain.models.Data

interface DataRepository {
    public fun getRequest(): Data?
}