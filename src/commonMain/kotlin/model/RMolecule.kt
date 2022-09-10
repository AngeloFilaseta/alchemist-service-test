package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RMolecule {

    val name: String

}

data class RMoleculeImpl(override val name: String) : RMolecule