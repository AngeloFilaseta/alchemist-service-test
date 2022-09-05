import kotlinx.serialization.Serializable

@Serializable
data class SimulationState(val running: Boolean) {

    companion object {
        const val path = "/simulationState"
    }

}
