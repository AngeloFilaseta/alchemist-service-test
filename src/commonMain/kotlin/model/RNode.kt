package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RNode {

    val id: Int

    //val reactions: List<RReaction>
}

@Serializable
data class RNodeImpl(
    override val id: Int
    //override val reactions: List<RReaction>
) : RNode