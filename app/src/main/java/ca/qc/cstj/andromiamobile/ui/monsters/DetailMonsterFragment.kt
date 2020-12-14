package ca.qc.cstj.andromiamobile.ui.monsters

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.FragmentMonsterDetailsBinding
import ca.qc.cstj.andromiamobile.models.Monster
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class DetailMonsterFragment: Fragment() {
    private var _binding : FragmentMonsterDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailMonsterFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_monster_details, container, false)
        _binding = FragmentMonsterDetailsBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Toast.makeText(this.context, args.planet!!.name, Toast.LENGTH_LONG).show()
        //(activity as MainActivity).supportActionBar?.title = args.monster!!.name

        binding.txvAtlasDetails.text = "#"+(args.monster as Monster).atlas
        binding.txvMonsterNameDetails.text = (args.monster as Monster).name
        binding.txvMonsterHealthDetails.text = (args.monster as Monster).health.toString()
        binding.txvMonsterAtkDetails.text = (args.monster as Monster).damage.toString()
        binding.txvMonsterSpeedDetails.text = (args.monster as Monster).speed.toString()
        binding.txvMonsterCriticalDetails.text = (args.monster as Monster).critical.toString()

        Glide.with(binding.root.context) //Autre bibliothèque Picasso
                .load((args.monster as Monster).asset)
                .into(binding.imgIconMonsterDetails)

    }

    companion object {
        fun newInstance() =
                DetailMonsterFragment().apply {}
    }
}
