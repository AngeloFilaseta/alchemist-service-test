import model.REnvironment
import model.RNode

interface Model<T> {

    var environment : REnvironment<T>

    var simState: SimulationState
}

class ModelImpl<T>(override var simState: SimulationState = SimulationState(false)) : Model<T> {
    override lateinit var environment: REnvironment<T>
}