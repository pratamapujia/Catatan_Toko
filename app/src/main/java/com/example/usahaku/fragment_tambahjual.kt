package com.example.usahaku

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.usahaku.Database.*
import com.example.usahaku.databinding.FragTambahjualBinding
import kotlinx.android.synthetic.main.frag_tambahjual.*
import java.text.SimpleDateFormat
import java.util.*

class fragment_tambahjual : Fragment() {

    var KEY_FRG = "msg_fragment2"
    var KEY_FRG1 = "msg_fragment"

    private lateinit var binding: FragTambahjualBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.frag_tambahjual,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val c1: Calendar = Calendar.getInstance()
        val year = c1.get(Calendar.YEAR)
        val month = c1.get(Calendar.MONTH)
        val day = c1.get(Calendar.DAY_OF_MONTH)
        val cdf1 = SimpleDateFormat("d/M/yyyy")
        val strdate1: String = cdf1.format(c1.getTime())
        var tempprodukViewModel: tempprodukViewModel = ViewModelProvider(this).get(tempprodukViewModel::class.java)
        var penjualanviewmodel: penjualanviewmodel = ViewModelProviders.of(this).get(penjualanviewmodel::class.java)
        binding.tanggal.setText(strdate1)
        rv_pilihjual.layoutManager = LinearLayoutManager(this.requireContext())
        rv_pilihjual.setHasFixedSize(true)
        val adapter = tempprodukAdapter(this.requireContext())
        rv_pilihjual.adapter = adapter
        var db = Room.databaseBuilder(requireContext(),tempprodukdb :: class.java,"tabel_tempproduk").build()
        // var emp = tempproduk
        var tanggal = binding.tanggal.text.toString()
        var key1 = arguments?.getString("namapelanggan")
        //var key2 = arguments?.getString("namaproduk")
        if (key1 != null) {
            var namapel = arguments?.getString("namapelanggan")
            binding.namapelanggan.setText(namapel)
        }
        tempprodukViewModel.alltemps.observe(this.viewLifecycleOwner, androidx.lifecycle.Observer { temps -> temps?.let{
           adapter.settemps(it)

            adapter.setOnClickListener {
                val current = temps[it]

                tempprodukViewModel.delete(current)
            }}
        })
        binding.tanggal.setOnClickListener{
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                var get = "" + dayOfMonth.toString() + "/" + month.toString() + "/" + year.toString()
                binding.tanggal.setText(get)
            }, year, month, day)
            dpd.show()
        }
        binding.pilihpelanggan.setOnClickListener{
            val pelanggan = fragment_pelanggan()
            val mBundle = Bundle()
            mBundle.putString(KEY_FRG, "hayukbisa")
            pelanggan.setArguments(mBundle)

                it.findNavController().navigate(R.id.fragment_pelanggan,mBundle)
        }
        binding.pilihproduk.setOnClickListener{
            val produk = Frag_produk()
            val bundle = Bundle()
            bundle.putString(KEY_FRG1 ,"Mantab")
            produk.setArguments(bundle)
            it.findNavController().navigate(R.id.action_fragment_tambahjual_to_frag_produk,bundle)
        }
        binding.pembayaran.setOnClickListener{
            fun coba(){
                var tanggall = binding.tanggal.text.toString()
                val t = Thread(Runnable {
                    var hartot = 0
                    var produkk = mutableListOf("")
                    var jumlah = mutableListOf("")
                    var satuan = mutableListOf("")
                    var harsat = mutableListOf("")
                    var i = 1
                    db.tempprodukdao().alldatatemp().forEach {
                        var harga = it.hargatot
                        hartot += harga
                        produkk.add(i,it.namaprodukbeli)
                        jumlah.add(i,it.jumlahbeli.toString())
                        satuan.add(i,it.satuanprodukbeli)
                        harsat.add(i,it.hargasatuanbeli.toString())
                        Log.i("@apaan","tanggal ${tanggal}")
                        Log.i("@apaan","produk ${it.namaprodukbeli}")
                        Log.i("@apaan","hartot ${hartot}")
                        Log.i("@apaan","array ${produkk[i]}")
                        i++
                    }
                    var no = i-1
                    var x = 1
                    var namapel = binding.namapelanggan.text.toString()
                    if (no == 1){
                    var newproduk = penjualan(namapel,produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1],tanggall,hartot.toString().toInt())
                        penjualanviewmodel.insert(newproduk)
                    }
                    else if (no == 2){
                        var newproduk = penjualan(namapel,produkk[1]+" : "+jumlah[1]+" "+satuan[1]+" x "+harsat[1]+"\n"+produkk[2]+
                                " : "+jumlah[2]+" "+satuan[2]+" x "+harsat[2],tanggall,hartot.toString().toInt())
                        penjualanviewmodel.insert(newproduk)
                    }

                })
                t.start()
            }
            coba()
            //
            this.findNavController().popBackStack()
            this.findNavController().popBackStack()
            this.findNavController().popBackStack()
        }
    }
}

