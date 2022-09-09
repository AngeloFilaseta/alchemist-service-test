import it.unibo.alchemist.boundary.interfaces.OutputMonitor
import it.unibo.alchemist.model.interfaces.Actionable
import it.unibo.alchemist.model.interfaces.Environment
import it.unibo.alchemist.model.interfaces.Position
import it.unibo.alchemist.model.interfaces.Time
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class ServiceOutputMonitor<T, P : Position<out P>> : OutputMonitor<T, P> {

    override fun stepDone(environment: Environment<T, P>, reaction: Actionable<T>?, time: Time, step: Long) {
        println("[environment: ${environment.nodes.map { it.toR() }}, time: ${time}, step: ${step}]")
    }

    override fun initialized(environment: Environment<T, P>) {
        this.stepDone(environment, null, Time.ZERO, 0L)
    }

    override fun finished(environment: Environment<T, P>, time: Time, step: Long) {
        super.stepDone(environment, null, time, step)
    }

}