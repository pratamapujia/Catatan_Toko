package com.example.usahaku

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.penjualan
import com.example.usahaku.Database.produk
import com.example.usahaku.Database.produkviewmodel
import com.example.usahaku.databinding.FragmentProdukBinding
import kotlinx.android.synthetic.main.fragment_produk.*

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class Frag_produk : Fragment() {

    var keyp = "Pembelian"
    private lateinit var binding: FragmentProdukBinding
    var KEY_FRG1 = "msg_fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_produk,container,false)
        // Inflate the layout for this fragment
            return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_produk.layoutManager = LinearLayoutManager(this.requireContext())
        rv_produk.setHasFixedSize(true)
        val adapter = produkadapter()
        rv_produk.adapter = adapter
        var produkviewmodel:produkviewmodel = ViewModelProviders.of(this).get(produkviewmodel::class.java)
        produkviewmodel.getAllproduk().observe(this.viewLifecycleOwner, Observer <List<produk>>{
            adapter.submitList(it)
        })
        binding.buttonAddproduk.setOnClickListener{
            it.findNavController().navigate(R.id.action_frag_produk_to_tambahproduk)
        }
        ItemTouchHelper(
            object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(viewHolder.itemView.getContext())
                        // Judul
                        .setTitle("Warning")
                        // Pesan yang di tamopilkan
                        .setMessage("Ingin Dihapus ?")
                        .setPositiveButton("Ya", DialogInterface.OnClickListener(){ dialogInterface, i ->
                            produkviewmodel.delete(adapter.getprodukAt(viewHolder.adapterPosition))
                            Toast.makeText(activity, "Produk dihapus!", Toast.LENGTH_SHORT).show()
                        })
                        .setNegativeButton("Tidak", DialogInterface.OnClickListener { dialogInterface, i ->
                            Toast.makeText(activity, "Produk Tidak Terhapus", Toast.LENGTH_LONG).show()
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        })
                        .show()
                }
            }
        ).attachToRecyclerView(rv_produk)
        adapter.setOnItemClickListener(object : produkadapter.OnItemClickListener {
            override fun onItemClick(produk:produk) {
                if (arguments != null) {
                    var pesan = arguments?.getString(keyp)
                    if(pesan != null){//pembelian
                           val Produk = produk(produk.namaproduk,produk.deskproduk,produk.hargapokok,
                            produk.hargajual,produk.jumlah,produk.satuanproduk)
                        fragdialog_pilproduk(Produk).show(childFragmentManager,"")
                    }
                    else{ //penjualan
                        val Produk = produk(produk.namaproduk,produk.deskproduk,produk.hargapokok,
                            produk.hargajual,produk.jumlah,produk.satuanproduk)
                        Produk.id_produk = produk.id_produk
                        fragdialog_pilproduk(Produk).show(childFragmentManager,"")
                    }
                }//argument
                else{//update
                    val bundle = Bundle()
                    bundle.putInt("id", produk.id_produk)
                    bundle.putString("namaproduk", produk.namaproduk)
                    bundle.putString("deskproduk", produk.deskproduk)
                    bundle.putInt("hargajual", produk.hargajual)
                    bundle.putInt("hargapokok", produk.hargapokok)
                    bundle.putInt("jumlah", produk.jumlah)
                    bundle.putString("satuan", produk.satuanproduk)

                    view.findNavController().navigate(R.id.action_frag_produk_to_tambahproduk,bundle)
                }

                        }
        })
    }
}
