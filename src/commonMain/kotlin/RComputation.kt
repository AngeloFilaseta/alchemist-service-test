import model.REnvironment

expect interface MultiplatformComputation {
    open fun logMessage()
}

object RComputation : MultiplatformComputation {

    fun compute(environment: REnvironment): Int {
        logMessage()
        return environment.nodes.map { it.id }.average().toInt()
    }

}
