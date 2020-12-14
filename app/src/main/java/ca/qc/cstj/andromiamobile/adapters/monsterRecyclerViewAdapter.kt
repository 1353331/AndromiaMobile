package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderMonsterBinding
import ca.qc.cstj.andromiamobile.models.Monster
import ca.qc.cstj.andromiamobile.ui.monsters.MonstersFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.NumberFormat
import java.util.*

class MonsterRecyclerViewAdapter(var monsters: List<Monster> = listOf()) : RecyclerView.Adapter<MonsterRecyclerViewAdapter.ViewHolder>() {

    private lateinit var circularProgressDrawable: CircularProgressDrawable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_monster, parent, false)

        circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonsterRecyclerViewAdapter.ViewHolder, position: Int) {
        val monster = monsters[position]
        holder.bind(monster)
    }

    override fun getItemCount(): Int = monsters.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //BINDING Ajout ici
        private val binding = ViewholderMonsterBinding.bind(view)
        //TODO: Gestion de l'interface graphique d'une carte pour une planète
        //Afficher son nom et sa température
        private val txvMonsterName: TextView = binding.txvMonsterName
        private val txvAffinity: TextView = binding.txvAffinity
        private val imgIconMonster: ImageView = binding.imgIconMonster

        fun bind(monster: Monster) {
            txvMonsterName.text = monster.name
            txvAffinity.text = monster.affinity

            val requestOptions = RequestOptions().placeholder(circularProgressDrawable).error(R.drawable.logo)

            Glide.with(binding.root.context) //Autre bibliothèque Picasso
                .applyDefaultRequestOptions(requestOptions)
                .load(monster.asset)
                .into(imgIconMonster)

            binding.root.setOnClickListener {
                val direction = MonstersFragmentDirections.actionNavMonstersToDetailMonsterFragment(monster)

                it.findNavController().navigate(direction)
            }

        }

    }

}