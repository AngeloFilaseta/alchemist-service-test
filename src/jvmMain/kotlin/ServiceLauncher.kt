package it.unibo.alchemist.launch

import it.unibo.alchemist.AlchemistExecutionOptions
import it.unibo.alchemist.launch.SimulationLauncher
import it.unibo.alchemist.launch.Validation
import it.unibo.alchemist.loader.Loader
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ServiceLauncher: SimulationLauncher() {

    override val name: String = "Service Launcher"

    override fun additionalValidation(currentOptions: AlchemistExecutionOptions): Validation = Validation.OK()

    @OptIn(DelicateCoroutinesApi::class)
    override fun launch(loader: Loader, parameters: AlchemistExecutionOptions) {
        println("SERVICE LAUNCHER IS ON")
        val simulation = prepareSimulation<Any, Nothing>(loader, parameters, emptyMap<String, Any>())

        GlobalScope.launch {
            AlchemistService.launch(simulation)
        }
        println("simulation.run being called...")
        simulation.run()
        println("simulation.ran")
    }

}