package ca.qc.cstj.andromiamobile.ui.portals

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.adapters.ElementRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.adapters.KernelRecyclerViewAdapter
import ca.qc.cstj.andromiamobile.databinding.ActivityDetailPortalBinding
import ca.qc.cstj.andromiamobile.helpers.AlertDialogBuilder
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.helpers.TopSpacingItemDecoration
import ca.qc.cstj.andromiamobile.models.Exploration
import ca.qc.cstj.andromiamobile.models.Monster
import ca.qc.cstj.andromiamobile.models.Vault
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_detail_portal.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DetailPortalActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityDetailPortalBinding
    private lateinit var elementRecyclerViewAdapter: ElementRecyclerViewAdapter
    private lateinit var kernelRecyclerViewAdapter: KernelRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Portal"
        binding = ActivityDetailPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val portal: Exploration = intent.getSerializableExtra(INTENT_PORTAL) as Exploration
        binding.txvPortalDestination.text = portal.destination

        binding.btnQuitExploration.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (portal.monster != null) {
            binding.txvMonsterNamePortal.text = portal.monster.name

            Glide.with(binding.root.context) //Autre bibliothèque Picasso
                .load(portal.monster.assets)
                .into(binding.imgMonsterPortal)
            binding.imgMonsterPortal.visibility = VISIBLE

            binding.lblKernelPortal.visibility = VISIBLE
            kernelRecyclerViewAdapter = KernelRecyclerViewAdapter()
            binding.rcvKernelPortal.apply{
                layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = kernelRecyclerViewAdapter
                kernelRecyclerViewAdapter.kernel = portal.monster.kernel
                binding.rcvKernelPortal.adapter!!.notifyDataSetChanged()
            }

            btnCapturePortal.visibility = VISIBLE
            btnCapturePortal.setOnClickListener() {
                Fuel.post(Services.CAPTURE_SERVICE)
                        .jsonBody(Json.encodeToString(portal.monster)).response{result ->
                            when(result){
                                is Result.Success -> {
                                    AlertDialogBuilder.showAlertDialog("Success", "You captured the monster!", this)
                                    binding.txvMonsterNamePortal.visibility = GONE
                                    binding.imgMonsterPortal.visibility = GONE
                                    binding.btnCapturePortal.visibility = GONE
                                    binding.lblKernelPortal.visibility = GONE
                                    binding.rcvKernelPortal.visibility = GONE
                                }
                                is Result.Failure -> {
                                    AlertDialogBuilder.showAlertDialog("Failure", "You don't have enough elements to capture this monster", this)
                                }
                            }
                        }
            }
        }

        if (portal.vault != null) {
            binding.txvVaultFound.text = "Vault found!"
            binding.txvInoxPortal.text = "Inox: ${portal.vault.inox}"
            binding.txvInoxPortal.visibility = VISIBLE
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)

            elementRecyclerViewAdapter = ElementRecyclerViewAdapter()
            binding.rcvElementsPortal.apply{
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = elementRecyclerViewAdapter
                addItemDecoration(topSpacingItemDecoration)
                elementRecyclerViewAdapter.elements = portal.vault.elements
                binding.rcvElementsPortal.adapter!!.notifyDataSetChanged()
            }
        }
    }


    companion object {
        private const val INTENT_PORTAL = "portal"

        fun newIntent(context: Context, portal: Exploration): Intent {
            val intent = Intent(context, DetailPortalActivity::class.java)
            intent.putExtra(INTENT_PORTAL, portal)
            return intent
        }

    }
}