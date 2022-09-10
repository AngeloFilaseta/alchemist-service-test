package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface REnvironment<T> {

    val dimensions: Int

    val nodes: List<RNode<T>>

    val nodeCount : Int

    companion object {
        const val path = "/environment"
    }
}

data class REnvironmentImpl<T>(
    override val dimensions: Int,
    override val nodes: List<RNode<T>>
) : REnvironment<T> {
    override val nodeCount: Int = nodes.size
}