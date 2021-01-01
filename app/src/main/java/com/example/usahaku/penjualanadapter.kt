package com.example.usahaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.penjualan
import kotlinx.android.synthetic.main.penjualan_item.view.*
import androidx.recyclerview.widget.ListAdapter


class penjualanadapter : ListAdapter<penjualan, penjualanadapter.penjualanHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<penjualan>() {
            override fun areItemsTheSame(oldItem: penjualan, newItem: penjualan): Boolean {
                return oldItem.id_penjualan == newItem.id_penjualan
            }
            override fun areContentsTheSame(oldItem: penjualan, newItem:penjualan): Boolean {
                return oldItem.namapelanggan== newItem.namapelanggan && oldItem.produk == newItem.produk
                         && oldItem.tanggaljual == newItem.tanggaljual && oldItem.totalpembayaran == newItem.totalpembayaran
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): penjualanHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.penjualan_item, parent, false)
        return penjualanHolder(itemView)
    }
    override fun onBindViewHolder(holder: penjualanHolder, position: Int) {
        val currentpenjualan: penjualan = getItem(position)
        holder.textViewtanggal.text = currentpenjualan.tanggaljual
        holder.textViewnama.text = currentpenjualan.namapelanggan
        holder.textViewhargajual.text = currentpenjualan.totalpembayaran.toString()

    }
    fun getpenjualanAt(position: Int): penjualan {
        return getItem(position)
    }
    inner class penjualanHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewtanggal: TextView = itemView.tanggalpenjualan
        var textViewhargajual: TextView = itemView.hargapenjualan
        var textViewnama: TextView = itemView.namapenjualan


    }
    interface OnItemClickListener {
        fun onItemClick(penjualan: penjualan)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}