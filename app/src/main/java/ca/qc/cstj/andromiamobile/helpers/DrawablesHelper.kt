package ca.qc.cstj.andromiamobile.helpers

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class DrawablesHelper {
    companion object {
        fun getDrawable(c: Context, imgName:String) : Drawable {
            return ContextCompat.getDrawable(c, c.resources.getIdentifier(imgName.decapitalize(), "drawable", c.packageName))!!
        }
    }
}