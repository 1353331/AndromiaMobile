package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderElementBinding
import ca.qc.cstj.andromiamobile.models.Element
import java.util.*

class ElementRecyclerViewAdapter(var elements: List<Element> = listOf()) : RecyclerView.Adapter<ElementRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementRecyclerViewAdapter.ViewHolder, position: Int) {
        val element = elements[position]
        holder.bind(element)
    }

    override fun getItemCount(): Int = elements.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //EL :On bind le viewholder
        private val binding = ViewholderElementBinding.bind(view)

        //EL :On déclare les txv présents
        private val txvElementName: TextView = binding.txvElementName
        private val txvQuantity: TextView = binding.txvQuantity


        fun bind(element: Element) {
            //EL :On met le contenu à afficher dans les txv
            txvElementName.text = element.element
            txvQuantity.text = element.quantity.toString()
        }
    }

}