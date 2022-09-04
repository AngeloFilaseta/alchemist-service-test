package simulator.monitor

import it.unibo.alchemist.boundary.interfaces.OutputMonitor
import it.unibo.alchemist.model.interfaces.Actionable
import it.unibo.alchemist.model.interfaces.Environment
import it.unibo.alchemist.model.interfaces.Position
import it.unibo.alchemist.model.interfaces.Time

class ServiceMonitor<T, P : Position<out P>> : OutputMonitor<T, P> {

    override fun finished(environment: Environment<T, P>, time: Time, step: Long) {
        stepDone(environment, null, time, step)
    }

    override fun initialized(environment: Environment<T, P>) {
        stepDone(environment, null, Time.ZERO, 0)
    }

    override fun stepDone(environment: Environment<T, P>, reaction: Actionable<T>?, time: Time, step: Long) {
        println("[env: ${environment}, reaction: ${reaction}, time: ${time}, step: ${step}]")
    }

}