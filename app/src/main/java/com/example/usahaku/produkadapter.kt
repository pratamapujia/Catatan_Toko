package com.example.usahaku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.usahaku.Database.produk
import kotlinx.android.synthetic.main.produk_item.view.*


class produkadapter : ListAdapter<produk, produkadapter.produkHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<produk>() {
            override fun areItemsTheSame(oldItem: produk, newItem: produk): Boolean {
                return oldItem.id_produk == newItem.id_produk
            }
            override fun areContentsTheSame(oldItem: produk, newItem: produk): Boolean {
                return oldItem.namaproduk == newItem.namaproduk
                        && oldItem.deskproduk == newItem.deskproduk
                        && oldItem.hargapokok == newItem.hargapokok
                        && oldItem.hargajual == newItem.hargajual
                        && oldItem.jumlah == newItem.jumlah
                        && oldItem.satuanproduk == newItem.satuanproduk
            }
        }
    }
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): produkHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.produk_item, parent, false)
        return produkHolder(itemView)
    }
    override fun onBindViewHolder(holder: produkHolder, position: Int) {
        val currentproduk: produk = getItem(position)
        holder.textViewnama.text = currentproduk.namaproduk
        holder.textViewhargajual.text = currentproduk.hargajual.toString()
        holder.textViewjumlah.text = currentproduk.jumlah.toString()
        holder.textViewsatuan.text = currentproduk.satuanproduk }
    fun getprodukAt(position: Int): produk {
        return getItem(position)
    }
    inner class produkHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewnama: TextView = itemView.nama_produk
        var textViewhargajual: TextView = itemView.hargajual
        var textViewjumlah: TextView = itemView.jumlah
        var textViewsatuan: TextView = itemView.satuan
    }
    interface OnItemClickListener {
        fun onItemClick(produk: produk)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}