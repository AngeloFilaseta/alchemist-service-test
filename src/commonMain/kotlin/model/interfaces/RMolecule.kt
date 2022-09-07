package model.interfaces

import kotlinx.serialization.Serializable

@Serializable
sealed interface RMolecule {

    val name: String

}