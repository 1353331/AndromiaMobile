package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy

@Serializable
data class Monster(val name:String, val atlasNumber:Int, val health:Int, val damage:Int, val speed:Int, val critical:Double, val affinity:String,
                   val assets:String, val talents:List<String>, val kernel:List<String>, val hash:String, val href:String) :java.io.Serializable