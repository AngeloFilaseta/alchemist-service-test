package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RReaction {

    val inputContext: RContext

    val outputContext: RContext

}

@Serializable
data class RReactionImpl(
    override val inputContext: RContext,
    override val outputContext: RContext
) : RReaction