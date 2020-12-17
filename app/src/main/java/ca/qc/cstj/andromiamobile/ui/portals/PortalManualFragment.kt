package ca.qc.cstj.andromiamobile.ui.portals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.andromiamobile.adapters.MonsterRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.databinding.FragmentMonstersBinding
import ca.qc.cstj.andromiamobile.databinding.FragmentPortalManualBinding
import ca.qc.cstj.andromiamobile.helpers.AlertDialogBuilder
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.helpers.TopSpacingItemDecoration
import ca.qc.cstj.andromiamobile.models.Exploration
import ca.qc.cstj.andromiamobile.repositories.MonsterRepository
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.fragment_portal_manual.*
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PortalManualFragment : Fragment() {

    private var _binding : FragmentPortalManualBinding? =  null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalManualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnManualCode.setOnClickListener(){
            Fuel.get("${Services.EXPLORATION_PORTAL_SERVICE}/${edtManualCode.text}").responseJson() { _, _, result ->
                when(result){
                    is Result.Success -> {
                        val portal: Exploration = Json {ignoreUnknownKeys = true}.decodeFromString(result.value.content)
                        val intent = DetailPortalActivity.newIntent(binding.root.context, portal)
                        startActivity(intent)
                    }
                    is Result.Failure -> {
                        AlertDialogBuilder.showAlertDialog("Error", "No portal found", binding.root.context)
                    }
                }
            }
        }

    }

}