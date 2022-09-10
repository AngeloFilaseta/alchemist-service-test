package it.unibo.alchemist.launch

import AlchemistService
import ModelImpl
import it.unibo.alchemist.AlchemistExecutionOptions
import it.unibo.alchemist.core.interfaces.Simulation
import it.unibo.alchemist.loader.Loader
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ServiceLauncher: SimulationLauncher() {

    override val name: String = "Service Launcher"

    override fun additionalValidation(currentOptions: AlchemistExecutionOptions): Validation = Validation.OK()

    @OptIn(DelicateCoroutinesApi::class)
    override fun launch(loader: Loader, parameters: AlchemistExecutionOptions) {
        val simulation: Simulation<Int, Nothing> = prepareSimulation(loader, parameters, emptyMap<String, Any>())
        val model = ModelImpl<Int>()
        GlobalScope.launch {
            simulation.run()
        }
        AlchemistService.launch(simulation, model)

    }

}