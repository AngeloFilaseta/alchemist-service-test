package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface REnvironment {

    val dimensions: Int

    val nodes: List<RNode>

    val nodeCount : Int

    companion object {
        private const val environment =  "/environment"
        const val pathClient = "${environment}/client"
        const val pathServer = "${environment}/server"
    }
}

@Serializable
data class REnvironmentImpl(
    override val dimensions: Int,
    override val nodes: List<RNode>
) : REnvironment {
    override val nodeCount: Int = nodes.size
}