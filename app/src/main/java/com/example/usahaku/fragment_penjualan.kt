package com.example.usahaku

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.penjualan
import com.example.usahaku.Database.penjualanviewmodel
import com.example.usahaku.Database.produk
import com.example.usahaku.databinding.FragPenjualanBinding
import kotlinx.android.synthetic.main.frag_penjualan.*

class fragment_penjualan : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragPenjualanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.frag_penjualan,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_jual.layoutManager = LinearLayoutManager(this.requireContext())
        rv_jual.setHasFixedSize(true)
        val adapter = penjualanadapter()
        rv_jual.adapter = adapter
        var penjualanviewmodel:penjualanviewmodel = ViewModelProviders.of(this).get(penjualanviewmodel::class.java)
        penjualanviewmodel.getAllpenjualan().observe(this.viewLifecycleOwner, Observer <List<penjualan>>{
            adapter.submitList(it)
        })//KURANG LISTENER KE UPJUAL
        binding.buttonAddjual.setOnClickListener{
            it.findNavController().navigate(R.id.action_fragment_penjualan_to_fragment_tambahjual)
        }

        ItemTouchHelper(
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT).or(
                    ItemTouchHelper.DOWN)) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(viewHolder.itemView.getContext())
                        // Judul
                        .setTitle("Warning")
                        // Pesan yang di tamopilkan//
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            penjualanviewmodel.delete(adapter.getpenjualanAt(viewHolder.adapterPosition))
                            Toast.makeText(activity, "Penjualan dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(activity, "Penjualan Tidak Terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_jual)
        adapter.setOnItemClickListener(object : penjualanadapter.OnItemClickListener {
            override fun onItemClick(penjualan: penjualan) {
                //penjualan
                var jual = penjualan(penjualan.namapelanggan,penjualan.produk,penjualan.tanggaljual,penjualan.totalpembayaran.toString().toInt())
                jual.id_penjualan = penjualan.id_penjualan
                       // view.findNavController().navigate(R.id.action_fragment_penjualan_to_fragment_tambahjual,bundle)
                fragdialog_upjual(jual).show(childFragmentManager,"")
            }
        })
    }
}
