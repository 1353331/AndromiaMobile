package ca.qc.cstj.andromiamobile.ui.explorations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.andromiamobile.adapters.ExplorationRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.databinding.FragmentExplorationsBinding
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.TopSpacingItemDecoration
import ca.qc.cstj.andromiamobile.repositories.ExplorationRepository
import ca.qc.cstj.andromiamobile.ui.explorations.ExplorationsFragment
import kotlinx.coroutines.launch

class ExplorationsFragment : Fragment() {

    //EL :on crée le bind pour notre fragment
    private var _binding : FragmentExplorationsBinding? =  null
    private val binding get() = _binding!!

    private lateinit var explorationRecyclerViewAdapter: ExplorationRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //EL :On bind à notre fragment des explorations
        _binding = FragmentExplorationsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topSpacingItemDecoration = TopSpacingItemDecoration(30)

        explorationRecyclerViewAdapter = ExplorationRecyclerViewAdapter()

        binding.rcvExplorations.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = explorationRecyclerViewAdapter
            addItemDecoration(topSpacingItemDecoration)
        }

        lifecycleScope.launch {
            //EL : on va récupérer les explorations
            when(val result = ExplorationRepository.getExplorations()) {
                is RepositoryResult.Success -> {
                    //EL :on a réussi à aller les récupérer, donc on les affiche dans le recycleview
                    explorationRecyclerViewAdapter.explorations = result.data
                    binding.rcvExplorations.adapter!!.notifyDataSetChanged()
                }
                is RepositoryResult.Error -> {
                    //EL :on n'a pas réussi à aller les récupérer, donc on affiche le message d'erreur
                    Toast.makeText(this@ExplorationsFragment.context, result.exception.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    companion object {
        fun newInstance() =
            ExplorationsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}