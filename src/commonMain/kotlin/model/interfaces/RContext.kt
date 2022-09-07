package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
enum class RContext {

    GLOBAL,
    LOCAL,
    NEIGHBORHOOD;

    companion object {

        fun getWider(c1: RContext, c2: RContext): RContext {
            if (c1 == GLOBAL || c2 == GLOBAL) {
                return GLOBAL
            }
            return if (c1 == NEIGHBORHOOD || c2 == NEIGHBORHOOD) {
                NEIGHBORHOOD
            } else LOCAL
        }
    }

}
