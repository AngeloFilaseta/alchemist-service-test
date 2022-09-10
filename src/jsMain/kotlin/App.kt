import react.*
import kotlinx.coroutines.*
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.p
import components.TextSwitch

private val scope = MainScope()

val App = FC<Props> {
    var simulationState by useState(SimulationState(false))
    var serverComputation by useState(false)
    var computationResult by useState(0)

    useEffectOnce {
        scope.launch {
            simulationState = getSimulationState()
        }
    }

    h1 {
        +"Alchemist Simulator Client"
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

    button {
        onClick = {
            scope.launch {
                computationResult = if(serverComputation) {
                    getEnvironmentServerComputation()
                } else {
                    RComputation.compute(getEnvironmentClientComputation())
                }
            }
        }
        +"compute"
    }

    p {
        +"Computation result: $computationResult"
    }

    TextSwitch {
        checked = serverComputation
        onChange = {
            _, _, _ ->
            serverComputation = !serverComputation
        }
        text = "Server Computation:"
    }

}