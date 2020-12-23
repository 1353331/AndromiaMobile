package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable

@Serializable
data class Element(val name:String, val quantity:Int) :java.io.Serializable