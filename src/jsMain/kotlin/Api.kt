import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.browser.window
import model.REnvironment


val endpoint = window.location.origin // only needed until https://youtrack.jetbrains.com/issue/KTOR-453 is resolved

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getSimulationState(): SimulationState {
    return jsonClient.get(endpoint + SimulationState.path).body()
}

suspend fun changeSimulationState(simulationState: SimulationState) {
    jsonClient.post(endpoint + SimulationState.path) {
        contentType(ContentType.Application.Json)
        setBody(simulationState)
    }
}

suspend fun getEnvironmentServerComputation() : Int {
    return jsonClient.get(endpoint + REnvironment.pathServer).body()
}

suspend fun getEnvironmentClientComputation() : REnvironment {
    return jsonClient.get(endpoint + REnvironment.pathClient).body()
}




