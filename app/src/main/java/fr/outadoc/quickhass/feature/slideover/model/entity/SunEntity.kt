package fr.outadoc.quickhass.feature.slideover.model.entity

import fr.outadoc.mdi.toIcon
import fr.outadoc.quickhass.feature.slideover.model.State

class SunEntity(state: State) : Entity(state, "weather-sunny".toIcon()!!)