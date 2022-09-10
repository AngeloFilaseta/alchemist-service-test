package model

import kotlinx.serialization.Serializable

@Serializable
sealed interface RMolecule {

    val name: String

}

@Serializable
data class RMoleculeImpl(override val name: String) : RMolecule