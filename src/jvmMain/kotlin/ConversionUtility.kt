import it.unibo.alchemist.model.interfaces.Context
import it.unibo.alchemist.model.interfaces.Node
import it.unibo.alchemist.model.interfaces.Molecule
import it.unibo.alchemist.model.interfaces.Reaction
import model.interfaces.*

fun Context.toR() : RContext = when {
    this == Context.GLOBAL -> RContext.GLOBAL
    this == Context.LOCAL -> RContext.LOCAL
    else -> RContext.NEIGHBORHOOD;
}

fun Molecule.toR() : RMolecule = RMoleculeImpl(this.name)

fun <T> Reaction<T>.toR() : RReaction = RReactionImpl(this.inputContext.toR(), this.outputContext.toR())

fun <T> Node<T>.toR(): RNode<T> {
    return RNodeImpl(
        this.id,
        this.contents.mapKeys { it.key.toR() }, //TODO this is so incorrect
        this.reactions.map{ it.toR() }
    )
}