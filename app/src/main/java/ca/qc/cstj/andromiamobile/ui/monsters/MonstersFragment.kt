package ca.qc.cstj.andromiamobile.ui.monsters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.andromiamobile.adapters.MonsterRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.databinding.FragmentMonstersBinding
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.TopSpacingItemDecoration
import ca.qc.cstj.andromiamobile.models.Monster
import ca.qc.cstj.andromiamobile.repositories.MonsterRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class MonstersFragment : Fragment() {

    private var _binding : FragmentMonstersBinding? =  null
    private val binding get() = _binding!!

    private lateinit var monsterRecyclerViewAdapter: MonsterRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonstersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topSpacingItemDecoration = TopSpacingItemDecoration(30)

        //TODO: Afficher les planètes dans le Recycler View
        monsterRecyclerViewAdapter = MonsterRecyclerViewAdapter()

        binding.rcvMonsters.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = monsterRecyclerViewAdapter
            addItemDecoration(topSpacingItemDecoration)
        }

        lifecycleScope.launch {

            when(val result = MonsterRepository.getMonsters(MonstersFragmentArgs.fromBundle(requireActivity().intent.extras!!).accessToken)) {
                is RepositoryResult.Success -> {
                    monsterRecyclerViewAdapter.monsters = result.data
                    binding.rcvMonsters.adapter!!.notifyDataSetChanged()
                }
                is RepositoryResult.Error -> {
                    Toast.makeText(this@MonstersFragment.context, result.exception.message,Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    companion object {

        fun newInstance() =
            MonstersFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}