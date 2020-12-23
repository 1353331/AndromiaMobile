package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderElementBinding
import ca.qc.cstj.andromiamobile.helpers.DrawablesHelper
import ca.qc.cstj.andromiamobile.models.Element
import ca.qc.cstj.andromiamobile.models.ElementPortal
import com.bumptech.glide.Glide
import java.util.*

class ElementPortalRecyclerViewAdapter(var elements: List<ElementPortal> = listOf()) : RecyclerView.Adapter<ElementPortalRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementPortalRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementPortalRecyclerViewAdapter.ViewHolder, position: Int) {
        val element = elements[position]
        holder.bind(element)
    }

    override fun getItemCount(): Int = elements.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //EL :On bind le viewholder
        private val binding = ViewholderElementBinding.bind(view)

        //EL :On déclare les txv présents
        private val txvQuantity: TextView = binding.txvQuantity


        fun bind(element: ElementPortal) {
            Glide.with(binding.root.context)
                    .load(DrawablesHelper.getDrawable(binding.root.context, element.element))
                    .into(binding.imgElementList)
            txvQuantity.text = element.quantity.toString()
        }
    }

}