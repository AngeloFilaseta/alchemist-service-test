package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
sealed interface RNode<T> {

    val id: Int

    val contents: Map<RMolecule, T>

    val moleculeCount: Int

    val reactions: List<RReaction>
}

data class RNodeImpl<T>(
    override val id: Int,
    override val contents: Map<RMolecule, T>,
    override val reactions: List<RReaction>
) : RNode<T> {
    override val moleculeCount: Int = contents.values.size
}