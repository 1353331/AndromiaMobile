package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable

@Serializable
data class Exploration(val explorationDate:String, val destination:String, val vault:Vault?=null, val monster:Monster?=null):java.io.Serializable