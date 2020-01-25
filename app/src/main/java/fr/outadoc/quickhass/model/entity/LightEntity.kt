package fr.outadoc.quickhass.model.entity

import fr.outadoc.mdi.toIcon
import fr.outadoc.quickhass.model.EntityState

class LightEntity(state: EntityState) : BinaryEntity(state, "lightbulb".toIcon()) {

    val brightness: Int?
        get() = additionalAttributes.brightness?.let { (it / 256 * 100).coerceIn(0, 100) }

    companion object {
        const val DOMAIN = "light"
    }
}