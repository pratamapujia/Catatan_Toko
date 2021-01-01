package com.example.usahaku

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.usahaku.Database.produk
import com.example.usahaku.Database.produkdb
import com.example.usahaku.Database.produkviewmodel
import com.example.usahaku.databinding.FragmentTambahprodukBinding
import kotlinx.android.synthetic.main.fragment_tambahproduk.*


class tambahproduk : Fragment() {
    var idt = 0
   private lateinit var binding: FragmentTambahprodukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tambahproduk,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var produkviewmodel:produkviewmodel =
            ViewModelProviders.of(this).get(produkviewmodel::class.java)
        if (arguments != null) {
            var nama = arguments?.getString("namaproduk")
            var desk = arguments?.getString("deskproduk")
            idt = arguments?.getInt("id").toString().toInt()
            var hargap =arguments?.getInt("hargapokok")
            var hargajual = arguments?.getInt("hargajual")
            var jumlah = arguments?.getInt("jumlah")
            var satuan = arguments?.getString("satuan")

            binding.namaProduk.setText(nama)
            binding.deskproduk.setText(desk)
            binding.hargajual.setText(hargajual.toString())
            binding.hargapokok.setText(hargap.toString())
            binding.jumlah.setText(jumlah.toString())
            binding.satuan.setText(satuan.toString())
        }
        binding.saveproduk.setOnClickListener{

            val newproduk = produk (
                binding.namaProduk.text.toString(),
                binding.deskproduk.text.toString(),
                binding.hargapokok.text.toString().toInt(),
                binding.hargajual.text.toString().toInt(),
                binding.jumlah.text.toString().toInt(),
                binding.satuan.text.toString()
            )
            if (arguments != null) {
                    newproduk.id_produk = idt
                produkviewmodel.update(newproduk)
            }
            else {
                produkviewmodel.insert(newproduk)
            }
            this.findNavController().popBackStack()
        }
    }
}
