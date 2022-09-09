package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
sealed interface RReaction {

    val inputContext: RContext

    val outputContext: RContext

}

data class RReactionImpl(
    override val inputContext: RContext,
    override val outputContext: RContext
) : RReaction