package it.unibo.alchemist.launch

import ModelImpl
import ServiceOutputMonitor
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import it.unibo.alchemist.AlchemistExecutionOptions
import it.unibo.alchemist.boundary.interfaces.OutputMonitor
import it.unibo.alchemist.core.interfaces.Simulation
import it.unibo.alchemist.loader.Loader
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import model.REnvironment


object ServiceLauncher: SimulationLauncher() {

    override val name: String = "Service Launcher"

    override fun additionalValidation(currentOptions: AlchemistExecutionOptions): Validation = Validation.OK()

    @OptIn(DelicateCoroutinesApi::class)
    override fun launch(loader: Loader, parameters: AlchemistExecutionOptions) {
        val simulation: Simulation<Any, Nothing> = prepareSimulation(loader, parameters, emptyMap<String, Any>())
        val model = ModelImpl<Any>()
        GlobalScope.launch {
            simulation.run()
        }

        val outputMonitor : OutputMonitor<Any, Nothing> = ServiceOutputMonitor(model)
        simulation.addOutputMonitor(outputMonitor)

        embeddedServer(Netty, 9090) {
            install(ContentNegotiation) {
                json()
            }
            install(CORS) {
                allowMethod(HttpMethod.Get)
                allowMethod(HttpMethod.Post)
                allowMethod(HttpMethod.Delete)
                anyHost()
            }
            install(Compression) {
                gzip()
            }

            routing {
                get("/") {
                    call.respondText(
                        this::class.java.classLoader.getResource("index.html")!!.readText(),
                        ContentType.Text.Html
                    )
                }

                static("/") {
                    resources("")
                }

                route(REnvironment.pathClient) {
                    get {
                        call.respond(model.environment)
                    }
                }

                route(REnvironment.pathServer) {
                    get {
                        call.respond(RComputation.compute(model.environment))
                    }
                }

                route(SimulationState.path) {
                    get {
                        call.respond(model.simState)
                    }

                    post {
                        model.simState = call.receive()
                        when {
                            model.simState .running -> simulation.play()
                            else -> simulation.pause()
                        }
                        call.respond(HttpStatusCode.OK)
                    }
                }
            }
        }.start(wait = true)

    }

}