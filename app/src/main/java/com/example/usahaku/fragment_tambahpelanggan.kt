package com.example.usahaku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.usahaku.Database.pelanggan
import com.example.usahaku.Database.pelangganviewmodel
import com.example.usahaku.databinding.FragTambahpelangganBinding
import kotlinx.android.synthetic.main.frag_tambahpelanggan.*


class fragment_tambahpelanggan : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragTambahpelangganBinding
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
            R.layout.frag_tambahpelanggan,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pelangganviewmodel: pelangganviewmodel
                = ViewModelProviders.of(this).get(pelangganviewmodel::class.java)
        if (arguments != null) {
            masukin()
            binding.savepelanggan.setOnClickListener{
                val newpelanggan = pelanggan (
                    binding.namapelanggan.text.toString(),
                    binding.emailpelanggan.text.toString(),
                    binding.alamatpelanggan.text.toString(),
                    binding.notelpel.text.toString())
                val idt = arguments?.getInt("id").toString().toInt()
                newpelanggan.id_pelanggan = idt
                pelangganviewmodel.update(newpelanggan)
                //this.dismiss()
                this.findNavController().popBackStack()
            }
        }
        else{
        binding.savepelanggan.setOnClickListener{

            val newpelanggan = pelanggan (
                binding.namapelanggan.text.toString(),binding.emailpelanggan.text.toString(),
                binding.alamatpelanggan.text.toString(),binding.notelpel.text.toString()
                ///ANJAY KALO INTEGER GIMANA NGAMBILNYA .tostring.toint
            )
            pelangganviewmodel.insert(newpelanggan)
            //this.dismiss()
            this.findNavController().popBackStack()
        }
        }//else
    }
    fun masukin(){
        namapelanggan.setText(arguments?.getString("namapelanggan"))
        emailpelanggan.setText(arguments?.getString("email"))
        notelpel.setText(arguments?.getString("notelp"))
        alamatpelanggan.setText(arguments?.getString("alamat"))
    }
}
