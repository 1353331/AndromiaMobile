package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable

/*Cette classe est ajoutée parce que le serveur retourne 2 représentations différentes d'un élément
 ("name" et "quantity" pour l'inventaire de l'utilisateur, "element" et "quantity" pour les
 éléments retrouvés dans une Vault située dans un Portal)
*/

@Serializable
data class ElementPortal(val element:String, val quantity:Int) :java.io.Serializable