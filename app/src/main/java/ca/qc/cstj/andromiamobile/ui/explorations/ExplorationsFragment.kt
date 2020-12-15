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
        _binding = FragmentExplorationsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topSpacingItemDecoration = TopSpacingItemDecoration(30)

        //Appel à notre repository pour récupérer les planètes

        //val username = PlanetsFragmentArgs.fromBundle(requireActivity().intent.extras!!).username

        explorationRecyclerViewAdapter = ExplorationRecyclerViewAdapter()

        binding.rcvExplorations.apply {
            //layoutManager = GridLayoutManager(this.context,2)
            layoutManager = LinearLayoutManager(this.context)
            adapter = explorationRecyclerViewAdapter
            addItemDecoration(topSpacingItemDecoration)
        }

        lifecycleScope.launch {
            when(val result = ExplorationRepository.getExplorations()) {
                is RepositoryResult.Success -> {
                    explorationRecyclerViewAdapter.explorations = result.data
                    binding.rcvExplorations.adapter!!.notifyDataSetChanged()
                }
                is RepositoryResult.Error -> {
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