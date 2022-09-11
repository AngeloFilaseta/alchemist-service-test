import it.unibo.alchemist.model.interfaces.*
import model.*

fun Context.toR() : RContext = when {
    this == Context.GLOBAL -> RContext.GLOBAL
    this == Context.LOCAL -> RContext.LOCAL
    else -> RContext.NEIGHBORHOOD
}

fun <T> Reaction<T>.toR() : RReaction = RReactionImpl(this.inputContext.toR(), this.outputContext.toR())

fun<T> Node<T>.toR(): RNode {
    return RNodeImpl(
        this.id,
       this.reactions.map{ it.toR() }
    )
}

fun <T, P : Position<out P>> Environment<T,P>.toR(): REnvironment {
    return REnvironmentImpl(
        this.dimensions,
        this.nodes.map { it.toR() }
    )
}