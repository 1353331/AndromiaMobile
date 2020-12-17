package ca.qc.cstj.andromiamobile.helpers

import android.app.AlertDialog
import android.content.Context

class AlertDialogBuilder {
    companion object  {
        fun showAlertDialog(title: String, message:String, context: Context) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setPositiveButton("Ok") {_, _ ->}
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()
        }
    }
}