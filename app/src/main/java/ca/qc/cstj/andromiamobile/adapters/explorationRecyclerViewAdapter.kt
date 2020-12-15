package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderExplorationBinding
import ca.qc.cstj.andromiamobile.models.Exploration
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
        //BINDING Ajout ici
        private val binding = ViewholderExplorationBinding.bind(view)
        //TODO: Gestion de l'interface graphique d'une carte pour une planète
        //Afficher son nom et sa température
        private val txvDestinationName: TextView = binding.txvDestinationName
        private val txvExplorationDate: TextView = binding.txvExplorationDate


        fun bind(exploration: Exploration) {
            txvDestinationName.text = exploration.destination
            txvExplorationDate.text = exploration.explorationDate
        }

    }

}