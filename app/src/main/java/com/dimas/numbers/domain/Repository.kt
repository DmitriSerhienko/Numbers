package com.dimas.numbers.domain

import com.dimas.numbers.data.db.FactRepository
import com.dimas.numbers.data.network.NetworkRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    localRepo: FactRepository,
    remoteRepo: NetworkRepository
) {
    val remote = remoteRepo
    val local = localRepo
}
