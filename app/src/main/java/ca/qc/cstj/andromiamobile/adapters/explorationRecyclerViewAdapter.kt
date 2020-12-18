package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderExplorationBinding
import ca.qc.cstj.andromiamobile.models.Exploration
import java.text.SimpleDateFormat
import java.util.*

class ExplorationRecyclerViewAdapter(var explorations: List<Exploration> = listOf()) : RecyclerView.Adapter<ExplorationRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExplorationRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_exploration, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExplorationRecyclerViewAdapter.ViewHolder, position: Int) {
        val exploration = explorations[position]
        holder.bind(exploration)
    }

    override fun getItemCount(): Int = explorations.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //EL :Binding avec le viewholder
        private val binding = ViewholderExplorationBinding.bind(view)

        //Binder les txv
        private val txvDestinationName: TextView = binding.txvDestinationName
        private val txvExplorationDate: TextView = binding.txvExplorationDate
        private val txvExplorationHeure: TextView = binding.txvExplorationHeure

        //EL :Bind les txv du viewholder avec le contenu qu'ils doivent afficher
        fun bind(exploration: Exploration) {

            //EL :Afficher la destination
            txvDestinationName.text = exploration.destination

            //EL :changer la string contenant une date en type date
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CANADA_FRENCH).parse(exploration.explorationDate)!!

            //EL :Changer la date en string du pattern que l'on souhaite
            var formatter = SimpleDateFormat("yyyy-MM-dd", Locale.CANADA_FRENCH)
            val dateString = formatter.format(date)
            //EL :Afficher la date
            txvExplorationDate.text = dateString

            //EL :Changer la date pour seulement afficher l'heure, les minutes et les secondes
            formatter = SimpleDateFormat("HH:mm:ss", Locale.CANADA_FRENCH)
            val heureString = formatter.format(date)
            //EL :Afficher l'heures de l'exploration
            txvExplorationHeure.text = heureString
        }

    }

}