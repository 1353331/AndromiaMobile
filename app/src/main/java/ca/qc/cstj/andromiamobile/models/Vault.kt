package ca.qc.cstj.andromiamobile.models

import kotlinx.serialization.Serializable

@Serializable
data class Vault(val inox:Int, val elements:List<ElementPortal>) :java.io.Serializable