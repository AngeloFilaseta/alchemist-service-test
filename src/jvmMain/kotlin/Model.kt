import model.REnvironment
import model.RNode

interface Model {

    var environment : REnvironment

    var simState: SimulationState
}

class ModelImpl<T>(override var simState: SimulationState = SimulationState(false)) : Model {
    override lateinit var environment: REnvironment
}