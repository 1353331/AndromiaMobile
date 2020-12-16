package ca.qc.cstj.andromiamobile.ui.monsters

import android.app.ActionBar
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import ca.qc.cstj.andromiamobile.MainActivity
import ca.qc.cstj.andromiamobile.R
import ca.qc.cstj.andromiamobile.databinding.FragmentMonsterDetailsBinding
import ca.qc.cstj.andromiamobile.models.Monster
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ca.qc.cstj.andromiamobile.helpers.DrawablesHelper
import org.w3c.dom.Text

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

        binding.txvAtlasDetails.text = "# ${args.monster.atlasNumber}"
        binding.txvMonsterNameDetails.text = args.monster.name
        binding.txvMonsterHealthDetails.text = args.monster.health.toString()
        binding.txvMonsterAtkDetails.text = args.monster.damage.toString()
        binding.txvMonsterSpeedDetails.text = args.monster.speed.toString()
        binding.txvMonsterCriticalDetails.text = "${(args.monster.critical*100).format(2)}%"

        Glide.with(binding.root.context) //Autre bibliothèque Picasso
                .load((args.monster as Monster).assets)
                .into(binding.imgIconMonsterDetails)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.affinity))
            .into(binding.imgAffinityDetails)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.talents[0]))
            .into(binding.imgTalent1Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.talents[1]))
            .into(binding.imgTalent2Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.kernel[0]))
            .into(binding.imgKernel1Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.kernel[1]))
            .into(binding.imgKernel2Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.kernel[2]))
            .into(binding.imgKernel3Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.kernel[3]))
            .into(binding.imgKernel4Details)

        Glide.with(binding.root.context)
            .load(DrawablesHelper.getDrawable(binding.root.context, args.monster.kernel[4]))
            .into(binding.imgKernel5Details)

        var txvHash: TextView = TextView(binding.root.context)
        txvHash.text = args.monster.hash.slice(0..1)
        txvHash.setTextColor(ContextCompat.getColor(binding.root.context, R.color.text_color))
        binding.HashLayout.addView(txvHash)
        for (index in 0..9){
            val color = args.monster.hash.slice((index*6+2)..(index*6+7))
            Log.d("colorHash", color)
            txvHash = TextView(binding.root.context)
            txvHash.width = 35
            txvHash.height = 50
            txvHash.setBackgroundColor(Color.parseColor("#${color}"))
            binding.HashLayout.addView(txvHash)
        }
        txvHash = TextView(binding.root.context)
        txvHash.text = args.monster.hash.slice(62..63)
        txvHash.setTextColor(ContextCompat.getColor(binding.root.context, R.color.text_color))
        binding.HashLayout.addView(txvHash)

    }

    companion object {
        fun newInstance() =
                DetailMonsterFragment().apply {}
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)

}
