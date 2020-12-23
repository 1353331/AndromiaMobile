package ca.qc.cstj.andromiamobile.helpers

import android.app.AlertDialog
import android.content.Context

//Permet de Builder des dialogs affichées à l'utilisateur
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