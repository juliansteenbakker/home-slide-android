package fr.outadoc.quickhass.feature.slideover.rest

import fr.outadoc.quickhass.feature.slideover.model.Action
import fr.outadoc.quickhass.feature.slideover.model.Entity
import fr.outadoc.quickhass.feature.slideover.model.Service
import fr.outadoc.quickhass.feature.slideover.model.State

interface EntityRepository {

    suspend fun getEntities(): Result<List<Entity>>

    suspend fun getServices(): Result<List<Service>>

    suspend fun callService(action: Action): Result<List<State>>
}