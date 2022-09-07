package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
sealed interface RNode<T> {

    val id: String

    val contents: Map<RMolecule, T>

    val moleculeCount: Int

    val reactions: List<RReaction>
}