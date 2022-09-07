package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
sealed interface RReaction {

    val inputContext: RContext

    val outputContext: RContext

}