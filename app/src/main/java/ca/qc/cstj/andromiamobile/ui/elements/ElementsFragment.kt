package ca.qc.cstj.andromiamobile.ui.elements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.andromiamobile.adapters.ElementRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.databinding.FragmentElementsBinding
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.TopSpacingItemDecoration
import ca.qc.cstj.andromiamobile.repositories.ElementRepository
import ca.qc.cstj.andromiamobile.ui.elements.ElementsFragment
import kotlinx.coroutines.launch

class ElementsFragment : Fragment() {

    //EL :on crée le bind pour notre fragment
    private var _binding : FragmentElementsBinding? =  null
    private val binding get() = _binding!!

    private lateinit var elementRecyclerViewAdapter: ElementRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        //EL :On bind à notre fragment des éléments
        _binding = FragmentElementsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topSpacingItemDecoration = TopSpacingItemDecoration(30)

        elementRecyclerViewAdapter = ElementRecyclerViewAdapter()

        binding.rcvElements.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = elementRecyclerViewAdapter
            addItemDecoration(topSpacingItemDecoration)
        }

        lifecycleScope.launch {
            //EL :on va récupérer les éléments
            when(val result = ElementRepository.getElements()) {
                is RepositoryResult.Success -> {
                    //EL :si on a réussi, on affiche les éléments dans le recyclerview
                    elementRecyclerViewAdapter.elements = result.data
                    binding.rcvElements.adapter!!.notifyDataSetChanged()
                }
                is RepositoryResult.Error -> {
                    //EL :On n'a pas réussi à aller récupérer les éléments, alors on affiche le message d'erreur
                    Toast.makeText(this@ElementsFragment.context, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        fun newInstance() =
                ElementsFragment().apply {
                    arguments = Bundle().apply {}
                }
    }
}