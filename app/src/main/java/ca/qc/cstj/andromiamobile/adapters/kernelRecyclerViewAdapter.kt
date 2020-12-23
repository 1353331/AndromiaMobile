package ca.qc.cstj.andromiamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.ViewholderElementBinding
import ca.qc.cstj.andromiamobile.databinding.ViewholderKernelBinding
import ca.qc.cstj.andromiamobile.helpers.DrawablesHelper
import ca.qc.cstj.andromiamobile.models.Element
import com.bumptech.glide.Glide

class KernelRecyclerViewAdapter(var kernel: List<String> = listOf()) : RecyclerView.Adapter<KernelRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KernelRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_kernel, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: KernelRecyclerViewAdapter.ViewHolder, position: Int) {
        val affinity = kernel[position]
        holder.bind(affinity)
    }

    override fun getItemCount(): Int = kernel.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewholderKernelBinding.bind(view)

        fun bind(affinity: String) {
            Glide.with(binding.root.context)
                    .load(DrawablesHelper.getDrawable(binding.root.context, affinity))
                    .into(binding.imgRcvKernel)
        }
    }

}