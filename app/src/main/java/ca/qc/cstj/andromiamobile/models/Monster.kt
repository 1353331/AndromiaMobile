package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable

@Serializable
data class Monster(val name:String, val atlas:Int, val health:Int, val damage:Int, val speed:Int, val critical:Double, val affinity:String,
                   val asset:String, val talent:List<String>, val kernel:List<String>, val href:String) :java.io.Serializable