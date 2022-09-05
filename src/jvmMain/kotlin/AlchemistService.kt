import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import it.unibo.alchemist.boundary.interfaces.OutputMonitor
import it.unibo.alchemist.core.interfaces.Simulation
import it.unibo.alchemist.model.interfaces.Position

class AlchemistService {
    companion object {
        fun <T,P : Position<out P>> launch(simulation: Simulation<T, P>) {

            val outputMonitor : OutputMonitor<T, P> = ServiceOutputMonitor()
            simulation.addOutputMonitor(outputMonitor)

            val shoppingList = mutableListOf(
                ShoppingListItem("Cucumbers", 1),
                ShoppingListItem("Tomatoes", 2),
                ShoppingListItem("Orange Juice", 3)
            )

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
                install(io.ktor.server.plugins.compression.Compression) {
                    gzip()
                }

                routing {
                    get("/") {
                        call.respondText(
                            this::class.java.classLoader.getResource("index.html")!!.readText(),
                            io.ktor.http.ContentType.Text.Html
                        )
                    }

                    static("/") {
                        resources("")
                    }

                    route(ShoppingListItem.path) {
                        get {
                            call.respond(shoppingList)
                        }
                        post {
                            shoppingList += call.receive<ShoppingListItem>()
                            call.respond(io.ktor.http.HttpStatusCode.OK)
                        }
                        delete("/{id}") {
                            val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                            shoppingList.removeIf { it.id == id }
                            call.respond(io.ktor.http.HttpStatusCode.OK)
                        }
                    }
                }
            }.start(wait = true)
        }
    }

}
