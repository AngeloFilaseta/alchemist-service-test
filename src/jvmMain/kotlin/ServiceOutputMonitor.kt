import it.unibo.alchemist.boundary.interfaces.OutputMonitor
import it.unibo.alchemist.model.interfaces.Actionable
import it.unibo.alchemist.model.interfaces.Environment
import it.unibo.alchemist.model.interfaces.Position
import it.unibo.alchemist.model.interfaces.Time


class ServiceOutputMonitor<T, P : Position<out P>>(private val model: Model<T>): OutputMonitor<T, P> {

    override fun stepDone(environment: Environment<T, P>, reaction: Actionable<T>?, time: Time, step: Long) {
        model.environment = environment.toR()
    }

    override fun initialized(environment: Environment<T, P>) {
        this.stepDone(environment, null, Time.ZERO, 0L)
    }

    override fun finished(environment: Environment<T, P>, time: Time, step: Long) {
        super.stepDone(environment, null, time, step)
    }

}