package ca.qc.cstj.andromiamobile.helpers

import android.content.Context
import android.graphics.drawable.Drawable

class DrawablesHelper {
    companion object {
        fun getDrawable(c: Context, imgName:String) : Drawable {
            return c.resources.getDrawable(c.resources.getIdentifier(imgName.decapitalize(), "drawable", c.packageName))
        }
    }
}