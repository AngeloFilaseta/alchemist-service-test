
import react.*
import kotlinx.coroutines.*
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.p
import adapters.Switch
import components.TextSwitch

private val scope = MainScope()

val App = FC<Props> {
    var simulationState by useState(SimulationState(false))
    var serverComputation by useState(false)

    useEffectOnce {
        scope.launch {
            simulationState = getSimulationState()
        }
    }

    h1 {
        +"Alchemist Simulator Client"
    }

    p {
        +simulationState.running.toString()
    }

    button {
        onClick = {
            scope.launch {
                changeSimulationState(SimulationState(!simulationState.running))
                simulationState = getSimulationState()
            }
        }
        if (simulationState.running) {
            +"pause"
        } else {
            +"play"
        }
    }

    TextSwitch {
        checked = serverComputation
        onChange = {
            _, _, _ ->
            serverComputation = !serverComputation
            console.log(serverComputation)
        }
        text = "Server Computation:"
    }

}