package com.example.usahaku

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.pelanggan
import com.example.usahaku.Database.pelangganviewmodel
import com.example.usahaku.Database.produk
import com.example.usahaku.Database.produkviewmodel
import com.example.usahaku.databinding.FragPelangganBinding
import com.example.usahaku.databinding.FragmentProdukBinding
import kotlinx.android.synthetic.main.frag_pelanggan.*
import kotlinx.android.synthetic.main.fragment_produk.*
@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class fragment_pelanggan : Fragment() {
    // TODO: Rename and change types of parameters
    var KEY_FRG = "msg_fragment2"
    private lateinit var binding: FragPelangganBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.frag_pelanggan,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val msg = arguments?.getString(KEY_FRG)

        rv_pelanggan.layoutManager = LinearLayoutManager(this.requireContext())
        rv_pelanggan.setHasFixedSize(true)
        val adapter = pelangganadapter()
        rv_pelanggan.adapter = adapter

        var pelangganviewmodel: pelangganviewmodel = ViewModelProviders.of(this).get(pelangganviewmodel::class.java)
        pelangganviewmodel.getAllpelanggan() .observe(this.viewLifecycleOwner, Observer <List<pelanggan>>{
            adapter.submitList(it)
        })

        binding.buttonAddpelanggan.setOnClickListener{
            it.findNavController().navigate(R.id.action_fragment_pelanggan_to_fragment_tambahpelanggan)
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
                        // Pesan yang di tamopilkan
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            pelangganviewmodel.delete(adapter.getpelangganAt(viewHolder.adapterPosition))

                            Toast.makeText(activity, "Pelanggan dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(activity, "Pelanggan Tidak Terhapus", Toast.LENGTH_SHORT).show()

                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_pelanggan)
        adapter.setOnItemClickListener(object : pelangganadapter.OnItemClickListener {
            override fun onItemClick(pelanggan: pelanggan) {
                if (arguments != null) {
                    val bundle = Bundle()
                    bundle.putString("namapelanggan", pelanggan.namapelanggan)
                    when (view.id) {
                        R.id.listpelanggan -> {
                            view.findNavController().navigate(
                                R.id.fragment_tambahjual, bundle)
                        }
                    }
                }
                else{
                    val bundle = Bundle()
                    bundle.putInt("id", pelanggan.id_pelanggan)
                    bundle.putString("namapelanggan", pelanggan.namapelanggan)
                    bundle.putString("alamat", pelanggan.alamatpelanggan)
                    bundle.putString("email", pelanggan.emailpelanggan)
                    bundle.putString("notelp", pelanggan.notelppelanggan)

                    view.findNavController().navigate(R.id.action_fragment_pelanggan_to_fragment_tambahpelanggan, bundle)

                }
            }
        }
        )}
}
